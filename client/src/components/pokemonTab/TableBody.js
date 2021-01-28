import React from 'react';

function TableBody(props) {

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
                {props.display["Base Stats"] ? addStats(data) : null}
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
        <tbody>
            {generateTable(props.tableData)}
        </tbody>
    )
}

export default TableBody;