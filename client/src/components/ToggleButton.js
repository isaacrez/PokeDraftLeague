import React from '../../node_modules/react';

function ToggleButton(props) {

    return (
        <label className="switch">
            <input type="checkbox"
                htmlFor={props.for}
                onChange={e => props.toggle(e.target.checked)} 
                defaultChecked={props.checked} />
            <span className="slider round"></span>
        </label>
    )
}

export default ToggleButton;