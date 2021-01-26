import React from 'react';

function PokeTable() {
    const [tableData, setTableData] = React.useState([]);
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);
    const [totalPages, setTotalPages] = React.useState(Math.ceil(1184 / pageSize));

    React.useEffect(() => {
        setTableData([]);
        fetchPokeData();
    }, [page]);

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
        tableData.forEach(data => formattedTable.push(generateTableEntry(data)));
        return formattedTable;
    }
    
    function generateTableEntry(data) {
        return (
            <tr>
                <td className="align-middle"><img src={data.sprites.front_default} alt={"Image of a " + data.name} /></td>
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
        <div className="mx-3">
            <table className="table table-secondary table-striped table-hover">
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
            <div className="d-flex justify-content-between align-items-center">
                <div>
                    <p className="mb-0">Page {page} of {totalPages}</p>
                </div>

                <div>
                    <button className="btn btn-secondary mx-1"
                        onClick={() => (setPage(page - 5))}>&#x00AB;</button>
                    <button className="btn btn-secondary mx-1"
                        onClick={() => (setPage(page - 1))}>←</button>
                    <button className="btn btn-secondary mx-1"
                        onClick={() => (setPage(page + 1))}>→</button>
                    <button className="btn btn-secondary mx-1"
                        onClick={() => (setPage(page + 5))}>&#x00BB;</button>
                </div>
                
                <div className="d-flex">
                    <p className="mb-0">Entries per page</p>
                    <select className="ml-3">
                        <option>5</option>
                        <option>10</option>
                        <option>25</option>
                    </select>
                </div>
            </div>
        </div>
    );
}

export default PokeTable;