import React from 'react';

function Schedule(props) {
    return props.league.id === -1 ?
        <EmptySchedule /> :
        <RegularSchedule league={props.league} />;
}

function RegularSchedule(props) {
    const [matches, setMatches] = React.useState([]);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/match/schedule/" + props.league.id;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(matchData => setMatches(matchData))
            .catch(error => console.log(error));
    }, [])

    function makeTableContent() {
        return matches.map(match => 
            <tr>
                <td>{match.scheduledWeek}</td>
                <td>{match.teams[0].name}</td>
                <td>{match.teams[1].name}</td>
            </tr>
        );
    }

    return (
        <div className="full-stripe">
            <h1>Match Schedule</h1>
            <h2>{props.league.name}</h2>
            <table className="w-100 table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Week</th>
                        <th>Team</th>
                        <th>Team</th>
                    </tr>
                </thead>
                <tbody>
                    {makeTableContent()}
                </tbody>
            </table>
        </div>
    )
}

function EmptySchedule() {
    return (
        <div className="full-stripe">
            <h1>Match Schedule... is Missing!</h1>
            <h2>Select a league first!</h2>
            <p>You can select a league under the "Home" tab</p>
        </div>
    )
}

export default Schedule;