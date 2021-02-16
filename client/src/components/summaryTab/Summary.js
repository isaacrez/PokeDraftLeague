import React from 'react';
import DropdownSelector from '../general/DropdownSelector';

const NO_TEAM_SELECT = "";

function Summary(props) {

    const [rosters, setRosters] = React.useState([]);
    const [team, setTeam] = React.useState("");

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/roster/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(rosterData => setRosters(rosterData))
            .catch(error => console.log(error));

    }, [props.league.id])

    return (
        <div className="full-stripe">
            <h1>Team Summary</h1>
            <h2 className="fancy">{props.league.name}</h2>
            <h2>{team.name}</h2>
            
            <DropdownSelector 
                setValue={setTeam}
                values={rosters.map(r => r.team.name)}
                purpose={"Team"}
                DEFAULT_VALUE={NO_TEAM_SELECT} />
        </div>
    )
}

export default Summary;