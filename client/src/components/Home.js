import React from '../../node_modules/react';

function Home(props) {
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

    return(
        <div className="full-stripe">
            <h1>Welcome!</h1>

            <p>
                To get started, select your league from
                the dropdown menu below
            </p>

            <div className="select-wrapper">
                <select
                    onChange={(e) => {props.setLeague(e.target.value)}}
                    defaultValue="">
                    <option value="">Select a league</option>
                    {createOptions(allLeagues)}
                </select>
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