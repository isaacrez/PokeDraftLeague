import React from 'react';
import {addLabel, addUpTo} from '../../util/pokeEntry';

function LoadedEntry(props) {

    function addStats() {
        return Object.values(props.data.stats).map((stat, index) => 
        <td className="align-middle"
            key={`${props.data.id}-${index}`}>
            {stat}
        </td>);
    } 

    function addTyping() {
        return addUpTo(props.data.type.map((type, index) =>
            <td key={`${props.data.id}-${index}`}>
                <img src={`https://www.serebii.net/pokedex-bw/type/${type.toLowerCase()}.gif`}
                    alt={`${type} type`} />
            </td>
        ), 2);
    }


    function addAbilities() {
        return addUpTo(props.data.abilities.map((ability, index) =>
            <td key={`${props.data.id}-${index}`}>
                {ability}
            </td>
        ), 4);
    }

    function addLeagueStats() {
        return [
            <td key={"team"}>{props.data.team.acronym}</td>,
            <td key={"directKOs"}>{props.leagueStats.directKOs}</td>,
            <td key={"indirectKOs"}>{props.leagueStats.indirectKOs}</td>,
            <td key={"deaths"}>{props.leagueStats.deaths}</td>
        ];
    }

    const displayOptions = {
        "Base Stats": addStats,
        "Typing": addTyping,
        "Abilities": addAbilities,
        "League Stats": addLeagueStats
    }
    
    const cells = [
        addLabel(props.data),
        Object.keys(props.display)
            .map(d => props.display[d] ? displayOptions[d]() : null)
    ];

    const recolor = props.data.team.acronym !== "FREE" ? "taken" : "";

    return (<tr className={recolor}>{cells}</tr>);
}

export default LoadedEntry;