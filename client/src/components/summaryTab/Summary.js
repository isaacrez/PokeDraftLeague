import React from 'react';
import DropdownSelector from '../general/DropdownSelector';
import PokemonStats from './PokemonStats';
import TeamStats from './TeamStats';

const NO_TEAM_SELECT = "";

function Summary(props) {

    const [rosters, setRosters] = React.useState([]);
    const [teamName, setTeamName] = React.useState("");
    const [teamStats, setTeamStats] = React.useState(null);
    const currSelection = rosters.find(r => r.team.name === teamName);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/roster/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(rosterData => setRosters(rosterData))
            .catch(error => console.log(error));

    }, [props.league.id])

    React.useEffect(() => {
        if (currSelection) {
            let teamId = currSelection.team.id;
            let url = `http://localhost:8080/api/league/results/${props.league.id}/${teamId}`;
            fetch(url, {type: "GET"})
                .then(response => response.json())
                .then(data => setTeamStats(data))
                .catch(error => console.log(error));
        }
    }, [teamName]);

    return (
        <div className="full-stripe">
            <div className="mb-3">
                <DropdownSelector 
                    setValue={setTeamName}
                    values={rosters.map(r => r.team.name)}
                    purpose={"Team"}
                    DEFAULT={{LABEL: "None", VALUE: NO_TEAM_SELECT}} />
            </div>

            {teamStats && <SummaryHeader team={currSelection.team} teamStats={teamStats} />}

            <PokemonStats rosterInfo={currSelection} league={props.league} />
        </div>
    )
}

function SummaryHeader(props) {
    return (
        <div className="w-100 d-flex justify-content-around align-items-center">
            <img src={`${process.env.PUBLIC_URL}/img/logos/${props.team.acronym}.png`}
                className="xl-icon" />

            <div className="d-flex flex-column align-items-center">
                <h1>{props.team.name}</h1>
                <TeamStats teamStats={props.teamStats} />
            </div>
        </div>
    )
}

export default Summary;