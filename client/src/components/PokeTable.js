import React from 'react';

function PokeTable(props) {
    const [tableData, setTableData] = React.useState([]);

    React.useEffect(() => {
        setTableData([]);
        fetchPokeData();
    }, [props.page]);

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

    function generateTable(tableData) {
        let formattedTable = [];
        tableData.sort((a, b) => {return a.id - b.id});
        tableData.forEach(data => formattedTable.push(generateTableEntry(data)));
        return formattedTable;
    }

    function generateTableEntry(data) {
        return (
            <tr key={data.id}>
                {addLabel(data)}
                {addStats(data)}
            </tr>
        )
    }

    function addLabel(data) {
        return (
            <td className="d-flex flex-column justify-content-center align-items-center"
                key={data.id}>
                <img
                    src={data.sprites.other["official-artwork"].front_default}
                    className="icon"
                    alt={"Image of a " + data.name} />
                <p>{capitalize(data.name)}</p>
            </td>
        )
    }

    function addStats(data) {
        let columns = [];
        data.stats.forEach((stat, index) => 
            columns.push(
                <td className="align-middle"
                    key={data.id + "" + index}>
                    {stat.base_stat}
                </td>
            )
        );
        return columns;
    }
    
    function capitalize(string) {
        return string.replace(/^\w/, (c) => c.toUpperCase());
    }

    return (
        <div className="scrollable-table">
            <table className="table table-secondary table-striped table-hover mb-0">
                <thead className="thead-dark">
                    <tr>
                        <th>Pokémon</th>
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
    );
}

export default PokeTable;