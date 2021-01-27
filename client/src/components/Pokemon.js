import React from 'react';
import ToggleBar from './ToggleBar';
import SearchBar from './SearchBar';
import PokeTable from './PokeTable';
import TableNavBar from './TableNavBar';

function Pokemon() {
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);
    const [onDisplay, setOnDisplay] = React.useState({
        "Base Stats": true,
        "Typing": false,
        "Abilities": false,
        "League Stats": false
    });

    const [pokemon, setPokemon] = React.useState([]);
    React.useEffect(() => {
        fetch("http://localhost:8080/api/pokemon", {type: "GET"})
            .then(response => response.json())
            .then(data => data.map(e => e.urlID))
            .then(pokemonNames => setPokemon(pokemonNames));
    }, [])

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

            <SearchBar 
                options={pokemon}/>

            <PokeTable 
                page={pageInfo} />

            <TableNavBar 
                page={pageInfo} />
        </div>
    );
}

export default Pokemon;