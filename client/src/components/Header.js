import React from 'react';
import DropdownSelector from './general/DropdownSelector';

function Header(props) {

    const options = ["Home", "Leaderboard", "Schedule", "Team Summary", "Matches", "Pokémon"];

    return (
        <header>
            <nav className="w-100 d-flex justify-content-between align-items-center p-2">

                <h1 className="mb-0">
                    {props.league.name ? props.league.name : "Pokédraft Leagues Online"}
                </h1>

                <DropdownSelector 
                    setValue={props.setMainContent}
                    values={options}
                    purpose={""} />

            </nav>
        </header>
    )
}


export default Header;