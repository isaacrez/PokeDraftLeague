import React from 'react';
import RouletteSet from '../general/RouletteSet';

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
        return tiers.map(t => <TierSet key={t} tier={t} league={props.league} />);
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
            .then(data => data.map(d => ({
                imgUrl: `https://www.serebii.net/swordshield/pokemon/${d.imgId}.png`,
                title: d.name,
                subtitle: d.team.acronym
            })))
            .then(data => setData(data))
            .catch(error => console.log(error));
    }, [props.league.id, props.tier]);

    function recolor(dataPoint) {
        return dataPoint.subtitle !== "FREE";
    }

    return(<div className="bubble mb-4">
        <h1 className="text-center">Tier {props.tier}</h1>
        <RouletteSet data={data} recolorIf={recolor} />
    </div>)
}

export default Draft;