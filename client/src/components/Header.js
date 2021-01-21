import React from '../../node_modules/react';

function Header(props) {

    const [allLeagues, setAllLeagues] = React.useState([]);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/league";
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => setAllLeagues(leagueData))
            .catch((error) => console.log(error));
    }, [])

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
                <select
                    onChange={(e) => {changeLeague(e, props.setLeague)}}
                    defaultValue="">
                    <option value="">Select a league</option>
                    {createOptions(allLeagues)}
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

function createOptions(optionData) {
    let options = [];
    optionData.forEach(data => options.push(<option>{data.name}</option>));
    return options;
}

export default Header;