import React from 'react';
import DropdownSelector from './general/DropdownSelector';

function Header(props) {

    const initialOptions = props.accessiblePages
                            .filter(o => o.alwaysAccessible)
                            .map(o => o.label);

    const fullOptions = props.accessiblePages.map(o => o.label);

    function setNewPage(label) {
        let newPage = props.accessiblePages
                        .find(o => o.label === label)
                        .output
        props.setMainContent(newPage);
    }

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
                        setValue={setNewPage}
                        values={props.league.name ? fullOptions : initialOptions}
                        purpose={""} />
                </div>

            </nav>
        </header>
    )
}


export default Header;