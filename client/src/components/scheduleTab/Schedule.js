import React from 'react';
import EmptySchedule from './EmptySchedule';

const NO_LEAGUE_SELECTED = -1;
const NO_WEEK_SELECT = 0;

function Schedule(props) {
    return props.league.id === NO_LEAGUE_SELECTED ?
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
        options.unshift(<option key={NO_WEEK_SELECT} value={NO_WEEK_SELECT}>All</option>);
        return options;
    }

    function makeTableContent() {
        return matches
            .filter(match => week === NO_WEEK_SELECT || match.scheduledWeek === week)
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

export default Schedule;