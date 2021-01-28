import React from 'react';
import ToggleButton from './ToggleButton';

function ToggleBar(props) {
    function updateState(key, val) {
        props.setState(prevState => {
            let newState = {...prevState}
            newState[key] = val;
            return newState;
        });
    }

    function generateSwitches() {
        let switches = [];
        for (let label in props.state) {
            switches.push(
                <div className="d-flex flex-column align-items-center"
                    key={label}>
                    <ToggleButton for={label}
                        checked={props.state[label]}
                        toggle={(val) => updateState(label, val)} />
                    <label htmlFor={label}>{label}</label>
                </div>
            )
        }
        return switches;
    }

    return (
        <form className="w-100 d-flex justify-content-between align-items-center mb-2">
            {generateSwitches()}
        </form>
    )
}

export default ToggleBar;