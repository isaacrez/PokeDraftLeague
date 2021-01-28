import React from 'react';

function TableHeader(props) {

    const extensions = {
        "Base Stats": [
            <th key="HP">HP</th>,
            <th key="Atk">Atk</th>,
            <th key="Def">Def</th>,
            <th key="SpAtk">SpAtk</th>,
            <th key="SpDef">SpDef</th>,
            <th key="Spe">Spe</th>
        ]
    };

    return (
        <tr>
            <th>Pokémon</th>
            {props.display["Base Stats"] ? extensions["Base Stats"] : null}
        </tr>
    )
} 

export default TableHeader;