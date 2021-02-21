import React from 'react';
import {addLabel, addStats, addTyping, addAbilities, addLeagueStats, colorizeBy} from '../../util/pokeEntry';

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState({});

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/stats/${props.data.pokemon.id}/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }, [props.data, props.league.id]);

    return (
        <tr style={leagueStats.team && {backgroundColor: colorizeBy(leagueStats.team.acronym)}}>
            {addLabel(props.data)}
            {props.display["Base Stats"] && addStats(props.data)}
            {props.display["Typing"] && addTyping(props.data)}
            {props.display["Abilities"] && addAbilities(props.data)}
            {props.display["League Stats"] && addLeagueStats(leagueStats)}
        </tr>
    )
}

export default Entry;