import React from 'react';
import DropdownSelector from './general/DropdownSelector';
import PokemonStats from './summaryTab/PokemonStats';
import SummaryHeader from './summaryTab/SummaryHeader';

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
    }, [props.league.id])

    React.useEffect(() => {
        if (currSelection) {
            let teamId = currSelection.team.id;
            let leagueStatsUrl = `http://localhost:8080/api/league/results` 
                + `?leagueId=${props.league.id}&teamId=${teamId}`;
            fetch(leagueStatsUrl, {type: "GET"})
                .then(response => response.json())
                .then(data => setTeamStats(data))
                .catch(error => console.log(error));
        }
    }, [currSelection, props.league.id]);

    let mainContent = teamStats ? [
        <SummaryHeader team={currSelection.team} teamStats={teamStats} key={1} />,
        <PokemonStats team={currSelection.team} league={props.league} key={2} />
    ] : null;

    return (
        <div className="full-stripe">
            <div className="mb-3">
                <DropdownSelector 
                    setValue={setTeamName}
                    values={rosters.map(r => r.team.name)}
                    purpose={"Team"} />
            </div>

            {mainContent}
        </div>
    )
}

export default Summary;