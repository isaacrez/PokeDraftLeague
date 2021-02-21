import React from 'react';

function LeagueSelector(props) {

    const [allLeagues, setAllLeagues] = React.useState([]);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/league";
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => setAllLeagues(leagueData))
            .catch((error) => console.log(error));
    }, [])

    function createOptions() {
        return allLeagues.map(league => <option key={league.id}>{league.name}</option>);
    }

    function updateLeague(input) {
        let league = allLeagues.filter(league => league.name === input);
        if (league.length === 1) {
            props.setLeague(league[0])
        }
    }

    return (
        <div className="select-wrapper">
            <input
                list="leagues" 
                onChange={(e) => {updateLeague(e.target.value)}}
                placeholder="Select a League"
                />
            <datalist id="leagues">
                {createOptions()}
            </datalist>
        </div>
    );
}

export default LeagueSelector;