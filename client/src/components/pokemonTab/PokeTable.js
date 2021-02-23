import React from 'react';
import TableHeader from './TableHeader';
import Entry from './Entry';

function PokeTable(props) {
    const [pokemon, setPokemon] = React.useState([]);

    React.useEffect(() => {
        let offset = (props.page.current - 1) * props.page.size;
        let url = `http://localhost:8080/api/pokemon/full?`
            + `limit=${props.page.size}&offset=${offset}`;
        url += props.league.id === -1 ? "" : `&leagueId=${props.league.id}`;

        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => {
                setPokemon(data.results);
                props.page.setTotal(data.count);
            })
            .catch(error => console.log(error));
    }, [props.page, props.league.id]);

    const tableBody = pokemon.map(poke => 
        <Entry data={poke}
            display={props.display}
            league={props.league}
            key={poke.pokemon.id} />
    )

    return (
        <div className="scrollable-table tall">
            <table className="hoverable">
                <thead>
                    <TableHeader 
                        display={props.display} />
                </thead>
                <tbody>
                    {tableBody}
                </tbody>
            </table>                
        </div>
    );
}

export default PokeTable;