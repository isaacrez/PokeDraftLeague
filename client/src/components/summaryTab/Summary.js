import React from 'react';
import DropdownSelector from '../general/DropdownSelector';
import {cleanText} from '../../util/pokeEntry';

const NO_TEAM_SELECT = "";

function Summary(props) {

    const [rosters, setRosters] = React.useState([]);
    const [team, setTeam] = React.useState("");
    const [teamStats, setTeamStats] = React.useState({});

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
    }, [team])

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

            {teamStats && 
            <div className="bubble my-3">
                <div className="d-flex justify-content-between">
                    <p>Played:</p>
                    <p>{teamStats.gamesPlayed}</p>
                </div>
                <div className="d-flex justify-content-between">
                    <p>Won:</p>
                    <p>{teamStats.gamesWon}</p>
                </div>
                <div className="d-flex justify-content-between">
                    <p>Lost:</p>
                    <p>{teamStats.gamesPlayed - teamStats.gamesWon}</p>
                </div>
                <div className="d-flex justify-content-between">
                    <p>Differential:</p>
                    <p>{teamStats.differential > 0 ? 
                        "+" + teamStats.differential
                        : teamStats.differential}</p>
                </div>
            </div>}

            <TableBody rosterInfo={rosters.filter(v => v.team.name === team)[0]} league={props.league} />
        </div>
    )
}

function TableBody(props) {

    function makeRows() {
        return props.rosterInfo.roster.map(pokemon => <Entry league={props.league} pokemon={pokemon} key={pokemon.id} />)
    }

    return props.rosterInfo ?  
        (<table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Played</th>
                    <th>KOs</th>
                    <th>Passive</th>
                    <th>Deaths</th>
                    <th>+/-</th>
                </tr>
            </thead>
            <tbody>
                {makeRows()}
            </tbody>
        </table>) :
        null;
}

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState({});

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/stats/${props.pokemon.id}/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }, [props.pokemon.id]);

    return (
        <tr key={props.pokemon.id}>
            <td>{cleanText(props.pokemon.urlID)}</td>
            <td>{leagueStats.gamesPlayed}</td>
            <td>{leagueStats.directKOs}</td>
            <td>{leagueStats.indirectKOs}</td>
            <td>{leagueStats.deaths}</td>
            <td>{leagueStats.directKOs + leagueStats.indirectKOs - leagueStats.deaths}</td>
        </tr>
    );
}

export default Summary;