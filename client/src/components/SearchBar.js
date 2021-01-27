import React from '../../node_modules/react';

function SearchBar(props) {

    function createOptions(optionData) {
        let options = [];
        optionData.forEach(data => options.push(<option key={data}>{data}</option>));
        return options;
    }

    return (
        <form className="search-bar">
            <input list="list"></input>
            <datalist id="list">
                {createOptions(props.options)}
            </datalist>
            <button type="button">Search</button>
        </form>
    );
}

export default SearchBar;