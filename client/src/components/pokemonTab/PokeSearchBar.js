import React from 'react';
import SearchBar from '../general/SearchBar';

function PokeSearchBar() {
    const [pokemon, setPokemon] = React.useState([]);
    React.useEffect(() => {
        fetch("http://localhost:8080/api/pokemon", {type: "GET"})
            .then(response => response.json())
            .then(data => data.map(e => e.urlID))
            .then(pokemonNames => setPokemon(pokemonNames));
    }, []);

    return (
        <SearchBar 
        options={pokemon}/>
    );
}

export default PokeSearchBar