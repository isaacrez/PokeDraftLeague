import React from 'react';

function Header(props) {

    const [visible, setVisible] = React.useState(false);
    const options = ["Home", "Leaderboard", "Schedule", "Team Summary", "Matches", "Pokémon"];

    function buildButtons() {
        return options.map(option => 
            <tr>
                <td>
                    <button onClick={() => {props.setMainContent(option); setVisible(false)}}
                        key={option}>
                        {option}
                    </button>
                </td>
            </tr>);
    }

    function buildDropdown() {
        return (
            <table>
                <tbody>
                    {buildButtons()}
                </tbody>
            </table>
        );
    }

    return (
        <header>
            <nav className="flex flex-column">
                <div className="w-100 d-flex justify-content-between align-items-center">
                    <button onClick={() => setVisible(v => !v)}
                        className="icon m-1">
                        <img src={`${process.env.PUBLIC_URL}/img/ui/tripleLine.png`} />
                    </button>

                    <h1>{props.league.name}</h1>

                    <h2 className="mr-3">{props.mainContent}</h2>
                </div>

                {visible && buildDropdown()}
            </nav>
        </header>
    )
}


export default Header;