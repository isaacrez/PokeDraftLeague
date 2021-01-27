import React from 'react';
import TableNavBar from './TableNavBar';
import PokeTable from './PokeTable';
import ToggleButton from './ToggleButton';

function Pokemon() {
    const [page, setPage] = React.useState(1);
    const [pageSize, setPageSize] = React.useState(5);
    const [onDisplay, setOnDisplay] = React.useState({
        "Base Stats": true,
        "Typing": false,
        "Abilities": false,
        "League Stats": false
    });

    function updateDisplay(key, val) {
        setOnDisplay(prevState => {
            let newState = {...prevState}
            newState[key] = val;
            return newState;
        });
    }

    function generateSwitches() {
        let switches = [];
        for (let label in onDisplay) {
            switches.push(
                <div className="d-flex flex-column align-items-center"
                    key={label}>
                    <ToggleButton for={label}
                        checked={onDisplay[label]}
                        toggle={(val) => updateDisplay(label, val)} />
                    <label htmlFor={label}>{label}</label>
                </div>
            )
        }
        return switches;
    }

    return (
        <div className="full-stripe">
            
            <form className="w-100 d-flex justify-content-between align-items-center mb-2">
                {generateSwitches()}
                <button className="btn btn-secondary h-75"
                    type="button">
                    Display
                </button>
            </form>

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