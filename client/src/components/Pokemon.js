import React from 'react';
import ToggleBar from './general/ToggleBar';
import PokeSearchBar from './pokemonTab/PokeSearchBar';
import PokeTable from './pokemonTab/PokeTable';
import TableNavBar from './general/TableNavBar';

function Pokemon(props) {
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);
    const [total, setTotal] = React.useState(1);
    const [onDisplay, setOnDisplay] = React.useState({
        "Base Stats": true,
        "Typing": false,
        "Abilities": false,
        "League Stats": false
    });

    React.useEffect(() => {
        props.league.id === -1 ? 
        setOnDisplay({
            "Base Stats": true,
            "Typing": false,
            "Abilities": false
        }) :
        setOnDisplay({
            "Base Stats": true,
            "Typing": false,
            "Abilities": false,
            "League Stats": false
        })
    }, [props.league])

    const pageInfo = {
        current: page,
        size: pageSize,
        total: total,
        setCurrent: setPage,
        setSize: setPageSize,
        setTotal: setTotal
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