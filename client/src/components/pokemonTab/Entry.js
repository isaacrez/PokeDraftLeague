import React from 'react';
import {addLabel, addStats, addTyping, addAbilities, addLeagueStats, colorizeBy} from '../../util/pokeEntry';

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState(null);
    const loaded = leagueStats !== null;

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/stats?pokeId=${props.data.pokemon.id}
                    &leagueId=${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }, [props.data, props.league.id]);

    const classLabel = loaded ? (leagueStats.team.name ? "taken" : "") : "";

    return loaded ? (
        <tr className={classLabel}>
            {addLabel(props.data)}
            {props.display["Base Stats"] && addStats(props.data)}
            {props.display["Typing"] && addTyping(props.data)}
            {props.display["Abilities"] && addAbilities(props.data)}
            {props.display["League Stats"] && addLeagueStats(leagueStats)}
        </tr>
    ) : null;
}

export default Entry;