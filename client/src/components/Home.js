import React from '../../node_modules/react';

function Home(props) {
    const [allLeagues, setAllLeagues] = React.useState([]);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/league";
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(response => response.map(obj => obj.name))
            .then(leagueData => setAllLeagues(leagueData))
            .catch((error) => console.log(error));
    }, [])

    function createOptions(optionData) {
        let options = [];
        optionData.forEach(data => options.push(<option>{data}</option>));
        return options;
    }

    function updateLeague(input) {
        console.log(input + " is a league: " + allLeagues.includes(input));
        console.log(allLeagues);
        if (allLeagues.includes(input)) {
            props.setLeague(input);
        }
    }

    return(
        <div className="full-stripe">
            <h1>Welcome!</h1>

            <p>
                To get started, select your league from
                the dropdown menu below
            </p>

            <div className="select-wrapper">
                <input
                    list="leagues" 
                    onChange={(e) => {updateLeague(e.target.value)}}
                    />
                <datalist id="leagues">
                    {createOptions(allLeagues)}
                </datalist>
            </div>

            <p>
                Then, access relevant information about 
                your league using the tabs above
            </p>

            <div className="h-25"></div>
        </div>
    )
}

export default Home;