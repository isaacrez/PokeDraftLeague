import React from 'react';

function TableHeader(props) {

    const headerContent = {
        "Base Stats": [["HP", "Atk", "Def", "SpAtk", "SpDef", "Spe"], 1],
        "Typing": [["Type"], 2],
        "Abilities": [["Abilities"], 4],
        "League Stats": [["Team", "KOs", "Passive", "Deaths"], 1]
    };

    const header = Object.keys(props.display)
        .filter(d => props.display[d])
        .map(d => headerContent[d][0]
            .map(label => <th key={label} colSpan={headerContent[d][1]}>{label}</th>))

    return (
        <tr>
            <th>Pokémon</th>
            {header}
        </tr>
    )
} 

export default TableHeader;