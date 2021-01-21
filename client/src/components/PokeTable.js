import React from 'react';

function PokeTable() {
    const [tableData, setTableData] = React.useState([]);

    React.useEffect(() => {
        setTableData([]);

        let pokemon = ["bulbasaur", "regigigas", "ditto"];
        pokemon.forEach(poke => {
            let url = "https://pokeapi.co/api/v2/pokemon/" + poke;
            fetch(url, {type: 'GET'})
                .then((response) => response.json())
                .then((pokeData) => setTableData(tableData => [...tableData, pokeData]))
                .catch((error) => console.log(error));
        })
    }, [])

    return (
        <div>
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
        </div>
    );
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

export default PokeTable;