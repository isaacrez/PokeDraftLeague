import React from 'react';
import ToggleBar from '../general/ToggleBar';
import PokeSearchBar from './PokeSearchBar';
import PokeTable from './PokeTable';
import TableNavBar from '../general/TableNavBar';

function Pokemon(props) {
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);
    const [onDisplay, setOnDisplay] = React.useState({
        "Base Stats": true,
        "Typing": false,
        "Abilities": false,
        "League Stats": false
    });

    const pageInfo = {
        current: page,
        size: pageSize,
        setCurrent: setPage,
        setSize: setPageSize
    }

    return (
        <div className="full-stripe">
            <ToggleBar 
                state={onDisplay} 
                setState={setOnDisplay}
                btnLabel="Display" />

            <PokeSearchBar />

            <PokeTable
                league={props.league} 
                page={pageInfo}
                display={onDisplay} />

            <TableNavBar 
                page={pageInfo} />
        </div>
    );
}

export default Pokemon;