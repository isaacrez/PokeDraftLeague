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
        ],
        "Typing": [
            <th key="type" colSpan="2">Type</th>
        ]
    };

    function addHeaders() {
        let headers = [];
        for (let label in extensions) {
            if (props.display[label]) {
                headers.push(extensions[label]);
            }
        }
        return headers;
    }

    return (
        <tr>
            <th>Pokémon</th>
            {addHeaders()}
        </tr>
    )
} 

export default TableHeader;