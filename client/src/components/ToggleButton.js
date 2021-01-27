import React from '../../node_modules/react';

function ToggleButton(props) {
    return (
        <label class="switch">
            <input type="checkbox" htmlFor={props.for} />
            <span class="slider round"></span>
        </label>
    )
}

export default ToggleButton;