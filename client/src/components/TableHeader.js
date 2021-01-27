import React from '../../node_modules/react';

function TableHeader(props) {

    const extensions = {
        "Base Stats": [
            <th>HP</th>,
            <th>Atk</th>,
            <th>Def</th>,
            <th>SpAtk</th>,
            <th>SpDef</th>,
            <th>Spe</th>
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