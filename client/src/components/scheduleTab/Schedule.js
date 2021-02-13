import React from 'react';
import EmptySchedule from './EmptySchedule';
import WeekSelector from './WeekSelector';
import TableBody from './TableBody';

const NO_LEAGUE_SELECTED = -1;
const NO_WEEK_SELECT = 0;

function Schedule(props) {
    return props.league.id === NO_LEAGUE_SELECTED ?
        <EmptySchedule /> :
        <RegularSchedule league={props.league} />;
}

function RegularSchedule(props) {
    const [matches, setMatches] = React.useState([]);
    const [teams, setTeams] = React.useState([]);
    const [week, setWeek] = React.useState(0);
    const [team, setTeam] = React.useState("");

    React.useEffect(() => {
        let scheduleUrl = "http://localhost:8080/api/match/schedule/" + props.league.id;
        fetch(scheduleUrl, {type: "GET"})
            .then(response => response.json())
            .then(matchData => setMatches(matchData))
            .catch(error => console.log(error));

        let leagueUrl = "http://localhost:8080/api/league/" + props.league.id;
        fetch(leagueUrl, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => setTeams(leagueData.teams))
            .catch(error => console.log(error));

    }, [props.league.id])

    function makeTeamOptions() {
        let options = teams.map(team => <option key={team.id}>{team.name}</option>);
        options.unshift(<option key={0}>All</option>)
        return options;
    }

    return (
        <div className="full-stripe">
            <h1>Match Schedule</h1>
            <h2>{props.league.name}</h2>

            <div className="w-100 d-flex justify-content-between">
                <WeekSelector
                    setWeek={setWeek}
                    matches={matches}
                    NO_WEEK_SELECT={NO_WEEK_SELECT} />

                <div className="d-flex minor-dropdown justify-content-around mb-3">
                    <label htmlFor="team" className="mr-3">Team</label>
                    <select id="team" onChange={e => setTeam(e.target.value)}>
                        {makeTeamOptions()}
                    </select>
                </div>
            </div>

            <TableBody 
                matches={matches}
                week={week}
                NO_WEEK_SELECT={NO_WEEK_SELECT} />
        </div>
    )
}

export default Schedule;