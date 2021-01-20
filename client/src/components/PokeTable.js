import React from 'react';

function PokeTable() {
    const [tableData, setTableData] = React.useState(null);

    React.useEffect(() => {
        let url = "https://pokeapi.co/api/v2/pokemon/regigigas";
        fetch(url, {type: 'GET'})
            .then((response) => response.json())
            .then((pokemon) => setTableData(pokemon))
            .catch((error) => console.log(error));
    }, [])

    return (
        <div>
            <table className="table table-secondary table-striped table-hover">
                <thead className="thead-dark">
                    <th>Name</th>
                    <th>Image</th>
                    <th>HP</th>
                    <th>Atk</th>
                    <th>Def</th>
                    <th>SpAtk</th>
                    <th>SpDef</th>
                    <th>Spe</th>
                </thead>
                <tbody>
                    {generateTableEntry(tableData)}
                </tbody>
            </table>
        </div>
    );
}

function generateTableEntry(data) {
    return data === null ? null : 
    (
        <tr>
            <td className="align-middle"><img src={data.sprites.front_default} /></td>
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