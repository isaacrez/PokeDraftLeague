import React from '../../node_modules/react';

function Header(props) {

    const options = ["Home", "Leaderboard", "Schedule", "Team Summary", "Matches", "Pokémon"];

    function makeButtons() {
        let buttons = [];
        options.forEach(option => 
            buttons.push(
                <button onClick={() => props.setMainContent(option)}
                    key={option}>
                    {option}
                </button>
            )    
        )
        return buttons;
    }

    return (
        <header>
            <nav>
                {makeButtons()}
            </nav>
        </header>
    )
}


export default Header;