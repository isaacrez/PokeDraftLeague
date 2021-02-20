import React from 'react';
import EmptySchedule from './EmptySchedule';
import DropdownSelector from '../general/DropdownSelector';
import TableBody from './TableBody';

const NO_LEAGUE_SELECTED = -1;
const NO_WEEK_SELECT = 0;
const NO_TEAM_SELECT = "";

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


    return (
        <div className="full-stripe">
            <div className="w-100 d-flex flex-wrap justify-content-between mb-3">
                <DropdownSelector 
                    setValue={setWeek}
                    values={[...new Set(matches.map(m => m.scheduledWeek))]}
                    purpose={"Week"}
                    DEFAULT={{LABEL: "All", VALUE: NO_WEEK_SELECT}} />

                <DropdownSelector 
                    setValue={setTeam}
                    values={teams.map(t => t.name)}
                    purpose={"Team"}
                    DEFAULT={{LABEL: "All", VALUE: NO_TEAM_SELECT}} />
            </div>

            <TableBody 
                matches={matches}
                week={week}
                team={team}
                NO_WEEK_SELECT={NO_WEEK_SELECT}
                NO_TEAM_SELECT={NO_TEAM_SELECT} />
        </div>
    )
}

export default Schedule;