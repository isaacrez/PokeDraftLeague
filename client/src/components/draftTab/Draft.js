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
                subtitle: d.team.acronym
            }))))
            .catch(error => console.log(error));
    }, [props.league.id, props.tier]);

    function recolor(dataPoint) {
        return dataPoint.subtitle !== "FREE";
    }

    return(<div className="mb-4">
        <h1 className="text-center">Tier {props.tier}</h1>
        <RouletteSet data={data} recolorIf={recolor} />
    </div>)
}

function RouletteSet(props) {

    const [isExpanded, setIsExpanded] = React.useState(false);

    function buildCards() {
        return props.data.map(d => 
            <Card imgUrl={d.imgUrl}
                title={d.title}
                subtitle={d.subtitle}
                recolor={props.recolorIf(d)} />)
    }

    return isExpanded ? 
        (<div className="d-flex flex-column align-items-center">
            <div className="d-flex roulette flex-wrap mb-1">
                {buildCards()}
            </div>
            <button onClick={() => setIsExpanded(false)}
                className="btn btn-secondary">Show less</button>
        </div>) :
        (<div className="d-flex flex-column align-items-center">
            <div className="d-flex roulette overflow-auto mb-1">
                {buildCards()}
            </div>
            <button onClick={() => setIsExpanded(true)}
                className="btn btn-secondary">Show all</button>
        </div>)
}

function Card(props) {
    let recolor = props.recolor ? "taken" : null;
    return (<div className={`card mx-1 ${recolor}`}>
        <img src={props.imgUrl} />
        <p>
            {props.title}
            <br />
            {props.subtitle}
        </p>
    </div>)
}

export default Draft;