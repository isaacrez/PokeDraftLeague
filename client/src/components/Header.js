import React from '../../node_modules/react';

function Header(props) {
    return (
        <header>
            <nav>
                <button>Home</button>
                <button>Leaderboard</button>
                <button>Schedule</button>
                <button>Team Summary</button>
                <button>Match Results</button>
                <button>Pokémon</button>
            </nav>
            <h1 className="text-center">{props.league} League</h1>
            <div className="select-wrapper">
                <select onChange={(e) => {changeLeague(e, props.setLeague)}}>
                    <option selected>Select a league</option>
                    <option>Engineering with Pixelmon S1</option>
                    <option>Engineering with Pixelmon S2</option>
                    <option>PROH World</option>
                </select>
            </div>
        </header>
    )
}

function changeLeague(e, setLeague) {
    if (e.target.value !== "Select a league") {
        setLeague(e.target.value);
    }
}

export default Header;