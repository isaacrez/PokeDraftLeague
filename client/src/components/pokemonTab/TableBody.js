import React from 'react';
import Entry from './Entry';

function TableBody(props) {

    function generateTable(tableData) {
        return tableData.sort((a, b) => a.id - b.id)
            .map(data => <Entry data={data} display={props.display} />);
    }

    return (
        <tbody>
            {generateTable(props.tableData)}
        </tbody>
    )
}

export default TableBody;