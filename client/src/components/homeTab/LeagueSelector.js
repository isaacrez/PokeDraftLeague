import React from 'react';

function LeagueSelector(props) {

    const [allLeagues, setAllLeagues] = React.useState([]);
    const options = allLeagues.map(league => <option key={league.id}>{league.name}</option>);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/league";
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => setAllLeagues(leagueData))
            .catch((error) => console.log(error));
    }, [])

    function updateLeague(input) {
        let league = allLeagues.find(league => league.name === input);
        league && props.setLeague(league);
    }

    return (
        <div className="select-wrapper">
            <input
                list="leagues" 
                onChange={(e) => {updateLeague(e.target.value)}}
                placeholder="Select a League"
                />
            <datalist id="leagues">
                {options}
            </datalist>
        </div>
    );
}

export default LeagueSelector;