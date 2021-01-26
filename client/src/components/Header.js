import React from '../../node_modules/react';
import PokeTable from './PokeTable';

function Header(props) {

    const [allLeagues, setAllLeagues] = React.useState([]);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/league";
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => setAllLeagues(leagueData))
            .catch((error) => console.log(error));
    }, [])

    function createOptions(optionData) {
        let options = [];
        optionData.forEach(data => options.push(<option>{data.name}</option>));
        return options;
    }

    function changeLeague(e, setLeague) {
        if (e.target.value !== "Select a league") {
            setLeague(e.target.value);
        }
    }
    
    return (
        <header>
            <nav>
                <button onClick={() => props.setMainContent(<h2>Home!</h2>)}>Home</button>
                <button onClick={() => props.setMainContent(<h2>Leaderboard!</h2>)}>Leaderboard</button>
                <button onClick={() => props.setMainContent(<h2>Schedule!</h2>)}>Schedule</button>
                <button onClick={() => props.setMainContent(<h2>Team Summary!</h2>)}>Team Summary</button>
                <button onClick={() => props.setMainContent(<h2>Matches!</h2>)}>Match Results</button>
                <button onClick={() => props.setMainContent(<PokeTable />)}>Pokémon</button>
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


export default Header;