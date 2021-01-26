import React from 'react';
import ArrowNavBar from './ArrowNavBar';

function TableNavBar(props) {
    return(
        <div className="d-flex justify-content-between align-items-center my-3">
            <div>
                <p className="mb-0">Page {props.page} of {props.totalPages}</p>
            </div>

            <ArrowNavBar setPage={props.setPage} />
            
            <div className="d-flex">
                <p className="mb-0">Entries per page</p>
                <select className="ml-3"
                    onClick={e => {props.setPageSize(e.target.value)}}>
                    <option>5</option>
                    <option>10</option>
                    <option>25</option>
                </select>
            </div>
        </div>
    );
}

export default TableNavBar;