import React from 'react';

function SearchBar(props) {
    
    const options = props.options.map(d => <option key={d}>{d}</option>);

    return (
        <form className="search-bar">
            <input list="list"></input>
            <datalist id="list">
                {options}
            </datalist>
            <button type="button">Search</button>
        </form>
    );
}

export default SearchBar;