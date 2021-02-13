import React from 'react';

function Entry(props) {

    const [data, setData] = React.useState({});
    const [leagueStats, setLeagueStats] = React.useState({});
    const [loaded, setLoaded] = React.useState(false);

    React.useEffect(() => {
        fetch(props.pokemon.url, {type: "GET"})
            .then(response => response.json())
            .then(pokeData => {
                setData(pokeData);
                getLeagueStats(pokeData);
            }).then(() => setLoaded(true))
            .catch(error => console.log(error));
    }, [props.pokemon]);

    function getLeagueStats(data) {
        let url = `http://localhost:8080/api/match/results/${data.id}/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }

    function addLabel(data) {
        return (
            <td key={data.id}>
                {data.sprites.other["official-artwork"].front_default && 
                <img
                    src={data.sprites.other["official-artwork"].front_default}
                    className="icon"
                    alt={`${data.name}`} />
                }
                <p>{cleanText(data.name)}</p>
            </td>
        )
    }

    function addStats(data) {
        return data.stats.map((stat, index) => 
            <td className="align-middle"
                key={`${data.id}-${index}`}>
                {stat.base_stat}
            </td>
        );
    }

    function addTyping(data) {
        return addUpTo(data.types.map((typeObj, index) =>
            <td key={`${data.id}-${index}`}>
                <img src={`https://www.serebii.net/pokedex-bw/type/${typeObj.type.name}.gif`}
                    alt={`${typeObj.type.name} type`} />
            </td>
        ), 2);
    }

    function addAbilities(data) {
        return addUpTo(data.abilities.map((abilityObj, index) =>
            <td key={`${data.id}-${index}`}>
                {cleanText(abilityObj.ability.name)}
            </td>
        ), 4);
    }

    function addLeagueStats() {
        return [
            <td key={"team"}>{leagueStats.team ? leagueStats.team.acronym : "FREE"}</td>,
            <td key={"directKOs"}>{leagueStats.directKOs}</td>,
            <td key={"indirectKOs"}>{leagueStats.indirectKOs}</td>,
            <td key={"deaths"}>{leagueStats.deaths}</td>
        ]
    }

    function addUpTo(columns, limit) {
        while (columns.length < limit) {
            columns.push(<td key={columns.length} />)
        }
        return columns
    }

    return (
        <tr style={leagueStats.team && {backgroundColor: colorizeBy(leagueStats.team.acronym)}}>
            {
                loaded ? [
                    addLabel(data),
                    props.display["Base Stats"] && addStats(data),
                    props.display["Typing"] && addTyping(data),
                    props.display["Abilities"] && addAbilities(data),
                    props.display["League Stats"] && addLeagueStats()
                ] : null
            }
        </tr>
    )
}

function cleanText(string) {
    return capitalize(string.replace(/-/g, ' '));
}

function capitalize(string) {
    return string.replace(/(\b[a-z](?!\s))/g, (c) => c.toUpperCase());
}

function colorizeBy(str) {
    return intToRGB(hashCode(str));
}

function hashCode(str) {
    let hash = 0;
    for (let i = 0; i < str.length; i++) {
        hash = str.charCodeAt(i) + ((hash << 12));
    }
    return hash;
}

function intToRGB(i) {
    let c = (i & 0x00FFFFFF)
        .toString(16)
        .toUpperCase();

    return "#00000".substring(0, 7 - c.length) + c;
}

export default Entry;