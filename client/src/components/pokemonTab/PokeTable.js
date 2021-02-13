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

    function buildUrl() {
        return "https://pokeapi.co/api/v2/pokemon?limit=" + props.page.size
         + "&offset=" + (props.page.current - 1) * props.page.size
    }

    function fetchPokeData() {
        let listPokemonUrl = buildUrl();
        fetch(listPokemonUrl, {type: "GET"})
            .then(response => response.json())
            .then(data => {
                data.results.forEach(pokemon => {
                    let statsUrl = pokemon.url;
                    fetch(statsUrl, {type: "GET"})
                        .then(response => response.json())
                        .then(pokeData => setTableData(tableData => [...tableData, pokeData]))
                        .catch(error => console.log(error));
                })
            })
            .catch(error => console.log(error));
    }

    return (
        <div className="scrollable-table">
            <table className="table table-secondary table-custom table-striped table-hover mb-0">
                <thead className="thead-dark">
                    <TableHeader 
                        display={props.display} />
                </thead>
                <TableBody 
                    display={props.display}
                    tableData={tableData} />
            </table>                
        </div>
    );
}

export default PokeTable;