import React from 'react';
import DropdownSelector from './general/DropdownSelector';

function Header(props) {

    const initialOptions = ["Home", "Pokémon"]
    const fullOptions = ["Home", "Leaderboard", "Schedule", "Team Summary", "Matches", "Draft", "Pokémon"];

    return (
        <header>
            <nav className="w-100 d-flex align-items-center p-2">

                <img src={`${process.env.PUBLIC_URL}/img/iconTextless.png`} 
                    className="icon" 
                    alt="" />

                <h1 className="ml-3 mb-0">
                    {props.league.name ? props.league.name : "Pokédraft Leagues Online"}
                </h1>

                <div className="ml-auto">
                    <DropdownSelector 
                        setValue={props.setMainContent}
                        values={props.league.name ? fullOptions : initialOptions}
                        purpose={""} />
                </div>

            </nav>
        </header>
    )
}


export default Header;