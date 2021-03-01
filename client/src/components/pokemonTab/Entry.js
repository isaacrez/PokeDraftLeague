import React from 'react';
import {addLabel, addStats, addTyping, addAbilities, addLeagueStats} from '../../util/pokeEntry';

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState(null);
    const loaded = leagueStats !== null;

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/stats?pokeId=${props.data.id}
                    &leagueId=${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }, [props.data, props.league.id]);

    return loaded ?
        <LoadedEntry display={props.display} data={props.data} leagueStats={leagueStats} />
        : null;
}

function LoadedEntry(props) {
    const displayOptions = {
        "Base Stats": addStats(props.data),
        "Typing": addTyping(props.data),
        "Abilities": addAbilities(props.data),
        "League Stats": addLeagueStats(props.leagueStats)
    }
    
    const cells = [
        addLabel(props.data),
        Object.keys(props.display)
            .map(d => props.display[d] ? displayOptions[d] : null)
    ];

    const recolor = props.data.team.acronym !== "FREE" ? "taken" : "";

    return (<tr className={recolor}>{cells}</tr>);
}

export default Entry;