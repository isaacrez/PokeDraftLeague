import React from 'react';

function DropdownSelector(props) {

    const defaultOption = props.DEFAULT
        ? <option key={props.DEFAULT.VALUE} value={props.DEFAULT.VALUE}>{props.DEFAULT.LABEL}</option>
        : null;

    const options = [
        defaultOption,
        props.values.map(v => <option key={v}>{v}</option>)
    ]

    return (
        <div className="minor-dropdown">
            <label htmlFor={props.purpose} className="mr-3">{props.purpose}</label>
            <select id={props.purpose} onChange={e => props.setValue(e.target.value)}>
                {options}
            </select>
        </div>
    );
}

export default DropdownSelector