import React from 'react';
import DropdownSelector from './general/DropdownSelector';

const NO_TEAM_SELECT = "";

function Match(props) {

    const [teams, setTeams] = React.useState([]);
    const [teamOne, setTeamOne] = React.useState("");
    const [teamTwo, setTeamTwo] = React.useState("");

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => setTeams(leagueData.teams))
            .catch(error => console.log(error));

    }, [props.league.id])

    return (<div className="full-stripe">
        <div className="d-flex w-100 justify-content-around">
            <DropdownSelector 
                        setValue={setTeamOne}
                        values={teams.map(t => t.name)}
                        purpose={"Team One"}
                        DEFAULT={{LABEL: "None", VALUE: NO_TEAM_SELECT}} />

            <DropdownSelector 
                        setValue={setTeamTwo}
                        values={teams.map(t => t.name)}
                        purpose={"Team Two"}
                        DEFAULT={{LABEL: "None", VALUE: NO_TEAM_SELECT}} />
        </div>
    </div>);
}

export default Match;