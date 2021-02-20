import React from 'react';

function DropdownSelector(props) {

    function makeOptions() {
        let options = props.values.map(v => <option key={v}>{v}</option>);
        props.DEFAULT && options.unshift(<option key={props.DEFAULT.VALUE} value={props.DEFAULT.VALUE}>
            {props.DEFAULT.LABEL}
        </option>)
        return options;
    }

    return (
        <div className="minor-dropdown">
            <label htmlFor={props.purpose} className="mr-3">{props.purpose}</label>
            <select id={props.purpose} onChange={e => props.setValue(e.target.value)}>
                {makeOptions()}
            </select>
        </div>
    );
}

export default DropdownSelector