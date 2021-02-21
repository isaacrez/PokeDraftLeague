import React from 'react';
import Entry from './Entry';

function TableBody(props) {

    function generateTable() {
        return props.pokemon.map(poke => 
            <Entry data={poke}
                display={props.display}
                league={props.league}
                key={poke.pokemon.id} />
        );
    }

    return (
        <tbody>
            {generateTable()}
        </tbody>
    )
}

export default TableBody;