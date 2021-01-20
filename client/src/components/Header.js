import React from '../../node_modules/react';

function Header(props) {
    return (
        <header className="header">
            <nav>
                <button>Home</button>
                <button>Leaderboard</button>
                <button>Schedule</button>
                <button>Team Summary</button>
                <button>Match Results</button>
                <button>Pokémon</button>
            </nav>
            <h1 className="text-center">{props.league} League</h1>
        </header>
    )
}

export default Header;