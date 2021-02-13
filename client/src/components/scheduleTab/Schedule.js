import React from 'react';

function Schedule(props) {
    return props.league.id === -1 ?
        <EmptySchedule /> :
        <RegularSchedule league={props.league} />;
}

function RegularSchedule(props) {
    const [matches, setMatches] = React.useState([]);
    const [week, setWeek] = React.useState(0);

    React.useEffect(() => {
        let url = "http://localhost:8080/api/match/schedule/" + props.league.id;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(matchData => setMatches(matchData))
            .catch(error => console.log(error));
    }, [props.league.id])

    function makeWeekOptions() {
        let options = new Set(matches.map(match => match.scheduledWeek));
        options = [...options].map(o => <option key={o}>{o}</option>);
        options.unshift(<option key={0} value={0}>All</option>);
        return options;
    }

    function makeTableContent() {
        return matches
            .filter(match => week === 0 || match.scheduledWeek === week)
            .map(match => 
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

            <div className="d-flex minor-dropdown w-25 justify-content-around mb-3">
                <label htmlFor="week">Week</label>
                <select id="week" onChange={e => setWeek(Number(e.target.value))}>
                    {makeWeekOptions()}
                </select>
            </div>

            <div className="scrollable-table">
                <table className="table table-secondary table-custom table-striped table-hover mb-0">
                    <thead className="thead-dark">
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