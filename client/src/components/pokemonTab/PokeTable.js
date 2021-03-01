import React from 'react';
import Entry from './Entry';

function PokeTable(props) {
    const [pokemon, setPokemon] = React.useState([]);
    const headerInfo = {
        "Base Stats": {content: ["HP", "Atk", "Def", "SpAtk", "SpDef", "Spe"], span: 1},
        "Typing": {content: ["Type"], span: 2},
        "Abilities": {content: ["Abilities"], span: 4},
        "League Stats": {content: ["Team", "KOs", "Passive", "Deaths"], span: 1}
    };
    
    const header = Object.keys(props.display)
        .filter(d => props.display[d])
        .map(d => headerInfo[d].content
            .map(label => <th key={label} colSpan={headerInfo[d].span}>{label}</th>))

    const tableBody = pokemon.map(poke => 
        <Entry data={poke}
            display={props.display}
            league={props.league}
            key={poke.id} />
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