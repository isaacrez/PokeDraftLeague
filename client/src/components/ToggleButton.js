import React from '../../node_modules/react';

function ToggleButton(props) {
    return (
        <label className="switch">
            <input type="checkbox" htmlFor={props.for} />
            <span className="slider round"></span>
        </label>
    )
}

export default ToggleButton;