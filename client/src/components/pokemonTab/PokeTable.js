import React from 'react';
import Entry from './Entry';

function PokeTable(props) {
    const [pokemon, setPokemon] = React.useState([]);
    const headerContent = {
        "Base Stats": [["HP", "Atk", "Def", "SpAtk", "SpDef", "Spe"], 1],
        "Typing": [["Type"], 2],
        "Abilities": [["Abilities"], 4],
        "League Stats": [["Team", "KOs", "Passive", "Deaths"], 1]
    };
    
    const header = Object.keys(props.display)
        .filter(d => props.display[d])
        .map(d => headerContent[d][0]
            .map(label => <th key={label} colSpan={headerContent[d][1]}>{label}</th>))

    const tableBody = pokemon.map(poke => 
        <Entry data={poke}
            display={props.display}
            league={props.league}
            key={poke.pokemon.id} />
    )

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

    return (
        <div className="scrollable-table tall">
            <table className="hoverable">
                <thead>
                    <tr>
                        <th>Pokémon</th>
                        {header}
                    </tr>
                </thead>
                <tbody>
                    {tableBody}
                </tbody>
            </table>                
        </div>
    );
}

export default PokeTable;