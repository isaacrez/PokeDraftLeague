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
            "Typing": addTyping
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

    function addTyping(data) {
        let columns = [];
        data.types.forEach(typeHolder => columns.push(
            <td className="align-middle"
                key={data.id  + "-"+ typeHolder.type.name}>
                {typeHolder.type.name}
            </td>)
        );

        columns.length == 1 && columns.push(<td key={data.id + "-none"} />)
        return columns;
    }
    
    function capitalize(string) {
        return string.replace(/^\w/, (c) => c.toUpperCase());
    }

    return (
        <tbody>
            {generateTable(props.tableData)}
        </tbody>
    )
}

export default TableBody;