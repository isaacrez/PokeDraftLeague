import React from '../../node_modules/react';

function Header() {
    return (
        <header className="header">
            <nav>
                <a href="#">Home</a>
                <a href="#">Leaderboard</a>
                <a href="#">Schedule</a>
                <a href="#">Team Summary</a>
                <a href="#">Match Results</a>
                <a href="#">Pokémon</a>
            </nav>
            <h1 className="text-center">PokéDraft League</h1>
        </header>
    )
}

export default Header;