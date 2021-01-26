import React from '../../node_modules/react';

function Header(props) {
    return (
        <header>
            <nav>
                <button onClick={() => props.setMainContent("Home")}>Home</button>
                <button onClick={() => props.setMainContent("Leaderboard")}>Leaderboard</button>
                <button onClick={() => props.setMainContent("Schedule")}>Schedule</button>
                <button onClick={() => props.setMainContent("TeamSummary")}>Team Summary</button>
                <button onClick={() => props.setMainContent("Matches")}>Match Results</button>
                <button onClick={() => props.setMainContent("PokeTable")}>Pokémon</button>
            </nav>
        </header>
    )
}


export default Header;