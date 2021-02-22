import React from 'react';
import DropdownSelector from '../general/DropdownSelector';
import PokemonStats from './PokemonStats';
import SummaryHeader from './SummaryHeader';

function Summary(props) {

    const [rosters, setRosters] = React.useState([]);
    const [teamName, setTeamName] = React.useState("");
    const [teamStats, setTeamStats] = React.useState(null);
    const currSelection = rosters.find(r => r.team.name === teamName);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/roster/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(rosterData => {
                setRosters(rosterData);
                setTeamName(rosterData[0].team.name);
            })
            .catch(error => console.log(error));
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [props.league.id])

    React.useEffect(() => {
        if (currSelection) {
            let teamId = currSelection.team.id;
            let leagueStatsUrl = `http://localhost:8080/api/league/results/${props.league.id}/${teamId}`;
            fetch(leagueStatsUrl, {type: "GET"})
                .then(response => response.json())
                .then(data => setTeamStats(data))
                .catch(error => console.log(error));
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [teamName]);

    return (
        <div className="full-stripe">
            <div className="mb-3">
                <DropdownSelector 
                    setValue={setTeamName}
                    values={rosters.map(r => r.team.name)}
                    purpose={"Team"} />
            </div>

            {teamStats && <SummaryHeader team={currSelection.team} teamStats={teamStats} />}
            {teamStats && <PokemonStats team={currSelection.team} league={props.league} />}
        </div>
    )
}

export default Summary;