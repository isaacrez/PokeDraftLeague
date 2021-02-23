import React from 'react';
import ArrowNavBar from './ArrowNavBar';

function TableNavBar(props) {

    const fullPageProp = {...props.page,
        max: Math.ceil(props.page.total / props.page.size)
    };

    const sizeOptions = [5, 10, 25, 50];
    const options = sizeOptions.map(d => <option key={d}>{d}</option>);

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
                    {options}
                </select>
            </div>
        </div>
    );
}

export default TableNavBar;