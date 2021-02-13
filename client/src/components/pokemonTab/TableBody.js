import React from 'react';

function TableBody(props) {

    function generateTable(tableData) {
        let formattedTable = [];
        tableData.sort((a, b) => {return a.id - b.id});
        tableData.forEach(data => formattedTable.push(generateTableEntry(data)));
        return formattedTable;
    }

    function generateTableEntry(data) {
        let columns = [];
        const mapping = {
            "Base Stats": addStats,
            "Typing": addTyping,
            "Abilities": addAbilities
        }

        for (let label in mapping) {
            props.display[label] && columns.push(mapping[label](data));
        }

        return (
            <tr key={data.id}>
                {addLabel(data)}
                {columns}
            </tr>
        )
    }

    function addLabel(data) {
        return (
            <td className="d-flex flex-column justify-content-center align-items-center"
                key={data.id}>
                {data.sprites.other["official-artwork"].front_default && 
                <img
                    src={data.sprites.other["official-artwork"].front_default}
                    className="icon"
                    alt={"Image of a " + data.name} />
                }
                <p>{cleanText(data.name)}</p>
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

    function addTyping(data) {
        let columns = [];
        data.types.forEach(typeObj => columns.push(
            <td key={data.id  + "-"+ typeObj.type.name}>
                <img src={"https://www.serebii.net/pokedex-bw/type/" + typeObj.type.name + ".gif"} />
            </td>)
        );

        columns = addUpTo(columns, 2);
        return columns;
    }

    function addAbilities(data) {
        let columns = [];
        data.abilities.forEach(abilityObj =>
            columns.push(
            <td key={data.id + abilityObj.ability.name}>
                {cleanText(abilityObj.ability.name)}
            </td>)
        )

        columns = addUpTo(columns, 4);
        return columns;
    }

    function addUpTo(columns, limit) {
        while (columns.length < limit) {
            columns.push(<td key={columns.length} />)
        }
        return columns
    }

    function cleanText(string) {
        return capitalize(string.replace(/-/g, ' '));
    }
    
    function capitalize(string) {
        return string.replace(/(\b[a-z](?!\s))/g, (c) => c.toUpperCase());
    }

    return (
        <tbody>
            {generateTable(props.tableData)}
        </tbody>
    )
}

export default TableBody;