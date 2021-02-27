import React from 'react';
import DropdownSelector from './general/DropdownSelector';
import { addLabel } from '../util/pokeEntry';

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
        
        <MatchData league={props.league} 
            teamIds={teamIds} />
    </div>);
}

function MatchData(props) {

    const [data, setData] = React.useState([]);

    const tableBody = data.map(d => <tr key={d.id}>
        {addLabel(d)}
        <td>{d.directKOs}</td>
        <td>{d.indirectKOs}</td>
        <td>{d.deaths}</td>
    </tr>);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/match/lineup`
                + `?teamId=${props.teamIds[0]}&teamId2=${props.teamIds[1]}`
                + `&leagueId=${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(matchData => setData(matchData))
            .catch(error => console.log(error));
    }, [props.teamIds, props.league.id])

    return (<div className="scrollable-table tall">
        <table>
            <thead>
                <tr>
                    <th>Pokemon</th>
                    <th>KOs</th>
                    <th>Passive KOs</th>
                    <th>Deaths</th>
                </tr>
            </thead>
            <tbody>
                {tableBody}
            </tbody>
        </table>
    </div>);
}

export default Match;