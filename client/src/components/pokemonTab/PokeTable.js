import React from 'react';
import TableHeader from './TableHeader';
import TableBody from './TableBody';

function PokeTable(props) {
    const [pokemon, setPokemon] = React.useState([]);

    React.useEffect(() => {
        let offset = (props.page.current - 1) * props.page.size;
        let url = `http://localhost:8080/api/pokemon/full?limit=${props.page.size}&offset=${offset}`;
    
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => setPokemon(data))
            .catch(error => console.log(error));
    }, [props.page]);

    return (
        <div className="scrollable-table">
            <table className="hoverable">
                <thead>
                    <TableHeader 
                        display={props.display} />
                </thead>
                <TableBody 
                    display={props.display}
                    pokemon={pokemon}
                    league={props.league} />
            </table>                
        </div>
    );
}

export default PokeTable;