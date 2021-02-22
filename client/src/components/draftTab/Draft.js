import React from 'react';

function Draft(props) {

    const [tiers, setTiers] = React.useState([]);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/tiers?leagueId=${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => setTiers(data))
            .catch(error => console.log(error));
    }, [props.league.id]);

    function buildTiers() {
        return tiers.map(t => <TierSet tier={t} league={props.league} />);
    }

    return (
        <div className="full-stripe">
            {buildTiers()}
        </div>
    )
}

function TierSet(props) {

    const [pokemon, setPokemon] = React.useState([]);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/tier?tier=${props.tier}`
                + `&leagueId=${props.league.id}`;

        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => setPokemon(data))
            .catch(error => console.log(error));
    }, [props.league.id, props.tier]);

    function buildCards() {
        return pokemon.map(p =>
            <Card imgUrl={`https://www.serebii.net/swordshield/pokemon/${p.imgId}.png`}
            title={p.name}
            subtitle="FREE" />)
    }

    return(<div className="w-100">
        <h1 className="text-center">Tier {props.tier}</h1>

        <div className="w-100 d-flex mx-3 flex-wrap">
            {buildCards()}
        </div>
    </div>)
}

function Card(props) {
    return (<div className="card">
        <img src={props.imgUrl} />
        <p>{props.title}</p>
        <p>{props.subtitle}</p>
    </div>)
}

export default Draft;