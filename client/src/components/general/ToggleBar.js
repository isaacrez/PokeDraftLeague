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

    const switches = Object.keys(props.state).map(label =>
        <div className="d-flex flex-column align-items-center"
            key={label}>
                <ToggleButton for={label}
                    checked={props.state[label]}
                    toggle={(val) => updateState(label, val)} />
                <label htmlFor={label}>{label}</label>
        </div>);

    return (<form className="w-100 d-flex justify-content-between align-items-center mb-2">
            {switches}
        </form>);
}

export default ToggleBar;