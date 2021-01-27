import React from 'react';
import TableNavBar from './TableNavBar';
import PokeTable from './PokeTable';

function Pokemon() {
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);

    return (
        <div className="full-stripe">
            
            <PokeTable 
                page={page}
                pageSize={pageSize} />

            <TableNavBar 
                setPage={setPage}
                page={page}
                setPageSize={setPageSize}
                pageSize={pageSize} />
        </div>
    );
}

export default Pokemon;