import React from 'react';
import DropdownSelector from './general/DropdownSelector';
import { addLabel } from '../util/pokeEntry';

function Match(props) {

    const [teams, setTeams] = React.useState([]);
    const [teamOne, setTeamOne] = React.useState({id: 0});
    const [teamTwo, setTeamTwo] = React.useState({id: 0});
    
    const teamIds = [teamOne.id, teamTwo.id];

    function setTeamId(teamName, setFunction) {
        setFunction(teams.find(t => t.name === teamName));
    }

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(leagueData => {
                setTeams(leagueData.teams);
                setTeamOne(leagueData.teams[0]);
                setTeamTwo(leagueData.teams[1]);
            })
            .catch(error => console.log(error));

    }, [props.league.id])

    return (<div className="full-stripe">
        <div className="d-flex w-100 justify-content-around mb-3">
            <DropdownSelector 
                        setValue={(name) => setTeamId(name, setTeamOne)}
                        values={teams
                            .filter(t => Number(t.id) !== teamTwo.id)
                            .map(t => t.name)}
                        purpose={"Team One"} />

            <DropdownSelector 
                        setValue={(name) => setTeamId(name, setTeamTwo)}
                        values={teams
                            .filter(t => Number(t.id) !== teamOne.id)
                            .map(t => t.name)}
                        purpose={"Team Two"} />
        </div>

        <div className="d-flex mx-auto align-items-center mb-3">
            <img src={`${process.env.PUBLIC_URL}/img/logos/${teamOne.acronym}.png`}
                alt={``}
                className="lg-icon" />
            <h2 className="mx-5">vs</h2>
            <img src={`${process.env.PUBLIC_URL}/img/logos/${teamTwo.acronym}.png`}
                alt={``}
                className="lg-icon" />
        </div>
        
        <MatchData league={props.league} 
            teamIds={teamIds} />
    </div>);
}

function MatchData(props) {

    const [data, setData] = React.useState([]);

    const tableBody = data.map(d => <tr key={d.id}>
        {addLabel(d)}
        <td className="fancy">{d.pokemon.team.acronym}</td>
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

    return (<div className="scrollable-table short">
        <table>
            <thead>
                <tr>
                    <th>Pokemon</th>
                    <th>Team</th>
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