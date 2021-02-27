import React from 'react';
import DropdownSelector from './general/DropdownSelector';

function Match(props) {

    const [teams, setTeams] = React.useState([]);
    const [teamOneId, setTeamOneId] = React.useState(0);
    const [teamTwoId, setTeamTwoId] = React.useState(0);
    
    const teamIds = [teamOneId, teamTwoId];

    function setTeamId(teamName, setFunction) {
        setFunction(teams.find(t => t.name === teamName).id);
    }

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => {
                setTeams(leagueData.teams)
                setTeamOneId(leagueData.teams[0].id)
                setTeamTwoId(leagueData.teams[1].id)
            })
            .catch(error => console.log(error));

    }, [props.league.id])

    return (<div className="full-stripe">
        <div className="d-flex w-100 justify-content-around">
            <DropdownSelector 
                        setValue={(name) => setTeamId(name, setTeamOneId)}
                        values={teams
                            .filter(t => Number(t.id) !== teamTwoId)
                            .map(t => t.name)}
                        purpose={"Team One"} />

            <DropdownSelector 
                        setValue={(name) => setTeamId(name, setTeamTwoId)}
                        values={teams
                            .filter(t => Number(t.id) !== teamOneId)
                            .map(t => t.name)}
                        purpose={"Team Two"} />
        </div>
    </div>);
}

export default Match;