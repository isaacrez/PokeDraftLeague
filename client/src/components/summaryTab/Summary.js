import React from 'react';
import DropdownSelector from '../general/DropdownSelector';
import PokemonStats from './PokemonStats';
import TeamStats from './TeamStats';

const NO_TEAM_SELECT = "";

function Summary(props) {

    const [rosters, setRosters] = React.useState([]);
    const [team, setTeam] = React.useState("");
    const [teamStats, setTeamStats] = React.useState(null);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/roster/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(rosterData => setRosters(rosterData))
            .catch(error => console.log(error));

    }, [props.league.id])

    React.useEffect(() => {
        let currSelection = rosters.find(r => r.team.name === team);
        if (currSelection) {
            let teamId = currSelection.team.id;
            let url = `http://localhost:8080/api/league/results/${props.league.id}/${teamId}`;
            fetch(url, {type: "GET"})
                .then(response => response.json())
                .then(data => setTeamStats(data))
                .catch(error => console.log(error));
        }
    }, [team]);

    return (
        <div className="full-stripe">
            <h2>{team.name}</h2>

            <div>
                <DropdownSelector 
                    setValue={setTeam}
                    values={rosters.map(r => r.team.name)}
                    purpose={"Team"}
                    DEFAULT={{LABEL: "None", VALUE: NO_TEAM_SELECT}} />
            </div>

            {teamStats && <TeamStats teamStats={teamStats} />}

            <PokemonStats rosterInfo={rosters.filter(v => v.team.name === team)[0]} league={props.league} />
        </div>
    )
}

export default Summary;