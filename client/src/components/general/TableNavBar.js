import React from 'react';
import ArrowNavBar from './ArrowNavBar';

function TableNavBar(props) {

    const fullPageProp = {...props.page,
        max: Math.ceil(props.page.total / props.page.size)
    }

    return(
        <div className="d-flex justify-content-between align-items-center w-100">
            <div>
                <p className="mb-0">Page {props.page.current} of {fullPageProp.max}</p>
            </div>

            <ArrowNavBar page={fullPageProp} />
            
            <div className="d-flex minor-dropdown">
                <p className="mb-0">Entries per page</p>
                <select className="ml-3"
                    onClick={e => {props.page.setSize(e.target.value)}}>
                    <option>5</option>
                    <option>10</option>
                    <option>25</option>
                    <option>50</option>
                </select>
            </div>
        </div>
    );
}

export default TableNavBar;