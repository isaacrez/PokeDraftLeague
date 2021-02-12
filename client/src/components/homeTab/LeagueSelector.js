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

    function createOptions(optionData) {
        let options = [];
        optionData.forEach(data => options.push(<option key={data.id}>{data.name}</option>));
        return options;
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
                />
            <datalist id="leagues">
                {createOptions(allLeagues)}
            </datalist>
        </div>
    );
}

export default LeagueSelector;