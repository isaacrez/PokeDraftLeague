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

    const [data, setData] = React.useState([]);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/tier?tier=${props.tier}`
                + `&leagueId=${props.league.id}`;

        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => setData(data.map(d => new Object({
                imgUrl: `https://www.serebii.net/swordshield/pokemon/${d.imgId}.png`,
                title: d.name,
                subtitle: "FREE"
            }))))
            .catch(error => console.log(error));
    }, [props.league.id, props.tier]);

    return(<div>
        <h1 className="text-center">Tier {props.tier}</h1>
        <RouletteSet data={data} />
    </div>)
}

function RouletteSet(props) {

    function buildCards() {
        return props.data.map(d => 
            <Card imgUrl={d.imgUrl}
                title={d.title}
                subtitle={d.subtitle} />)
    }

    return(<div className="mx-3 roulette">
        <div className="d-flex mx-3 justify-content-center overflow-auto">
            {buildCards()}
        </div>
    </div>)
}

function Card(props) {
    return (<div className="card">
        <img src={props.imgUrl} />
        <p>
            {props.title}
            <br />
            {props.subtitle}
        </p>
    </div>)
}

export default Draft;