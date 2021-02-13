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
        ],
        "Abilities": [
            <th key="abilities" colSpan="4">Abilities</th>
        ],
        "League Stats": [
            <th key="currentTeam">Team</th>,
            <th key="directKOs">Direct KOs</th>,
            <th key="indirectKOs">Indirect KOs</th>,
            <th key="deaths">Deaths</th>
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