import React from 'react';
import TableHeader from './TableHeader';
import TableBody from './TableBody';

function PokeTable(props) {
    const [tableData, setTableData] = React.useState([]);

    React.useEffect(() => {
        setTableData([]);
        fetchPokeData();
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [props.page.current, props.page.size]);

    function fetchPokeData() {
        let offset = (props.page.current - 1) * props.page.size;
        let listUrl = `https://pokeapi.co/api/v2/pokemon?limit=${props.page.size}&offset=${offset}`;

        fetch(listUrl, {type: "GET"})
            .then(response => response.json())
            .then(data => data.results.forEach(pokemon => getPokemonStats(pokemon)))
            .catch(error => console.log(error));
    }

    function getPokemonStats(pokemon) {
        let statsUrl = pokemon.url;

        fetch(statsUrl, {type: "GET"})
            .then(response => response.json())
            .then(pokeData => setTableData(tableData => [...tableData, pokeData]))
            .catch(error => console.log(error));
    }

    return (
        <div className="scrollable-table">
            <table>
                <thead>
                    <TableHeader 
                        display={props.display} />
                </thead>
                <TableBody 
                    display={props.display}
                    tableData={tableData}
                    league={props.league} />
            </table>                
        </div>
    );
}

export default PokeTable;