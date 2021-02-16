import React from 'react';
import {addLabel, addStats, addTyping, addAbilities, addLeagueStats, colorizeBy} from '../../util/pokeEntry';

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

    return (
        <tr style={leagueStats.team && {backgroundColor: colorizeBy(leagueStats.team.acronym)}}>
            {
                loaded ? [
                    addLabel(data),
                    props.display["Base Stats"] && addStats(data),
                    props.display["Typing"] && addTyping(data),
                    props.display["Abilities"] && addAbilities(data),
                    props.display["League Stats"] && addLeagueStats(leagueStats)
                ] : null
            }
        </tr>
    )
}

export default Entry;