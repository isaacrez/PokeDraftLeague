import React from 'react';
import TableNavBar from './TableNavBar';

function PokeTable() {
    const [tableData, setTableData] = React.useState([]);
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);

    React.useEffect(() => {
        setTableData([]);
        fetchPokeData();
    }, [page, pageSize]);

    function fetchPokeData() {
        let listPokemonUrl = "https://pokeapi.co/api/v2/pokemon?limit=" + pageSize + "&offset=" + (page - 1) * pageSize;
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

    function generateTable(tableData) {
        let formattedTable = [];
        tableData.sort((a, b) => {return a.id - b.id});
        tableData.forEach(data => formattedTable.push(generateTableEntry(data)));
        return formattedTable;
    }
    
    function generateTableEntry(data) {
        return (
            <tr key={data.id}>
                <td className="align-middle">
                    <img
                        src={data.sprites.other["official-artwork"].front_default}
                        className="icon"
                        alt={"Image of a " + data.name} />
                </td>
                <td className="align-middle">{capitalize(data.name)}</td>
                <td className="align-middle">{data.stats[0].base_stat}</td>
                <td className="align-middle">{data.stats[1].base_stat}</td>
                <td className="align-middle">{data.stats[2].base_stat}</td>
                <td className="align-middle">{data.stats[3].base_stat}</td>
                <td className="align-middle">{data.stats[4].base_stat}</td>
                <td className="align-middle">{data.stats[5].base_stat}</td>
            </tr>
        )
    }
    
    function capitalize(string) {
        return string.replace(/^\w/, (c) => c.toUpperCase());
    }

    return (
        <div className="full-stripe">
            <div className="scrollable-table">
                <table className="table table-secondary table-striped table-hover mb-0">
                    <thead className="thead-dark">
                        <tr>
                            <th>Image</th>
                            <th>Name</th>
                            <th>HP</th>
                            <th>Atk</th>
                            <th>Def</th>
                            <th>SpAtk</th>
                            <th>SpDef</th>
                            <th>Spe</th>
                        </tr>
                    </thead>
                    <tbody>
                        {generateTable(tableData)}
                    </tbody>
                </table>                
            </div>

            <TableNavBar 
                setPage={setPage}
                page={page}
                setPageSize={setPageSize}
                pageSize={pageSize} />
        </div>
    );
}

export default PokeTable;