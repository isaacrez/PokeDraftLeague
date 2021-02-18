import React from 'react';
import DropdownSelector from '../general/DropdownSelector';
import {addLeagueStats, cleanText} from '../../util/pokeEntry';

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

            <TableBody rosterInfo={rosters.filter(v => v.team.name === team)[0]} league={props.league} />
        </div>
    )
}

function TableBody(props) {

    function makeRows() {
        return props.rosterInfo.roster.map(pokemon => <Entry league={props.league} pokemon={pokemon} />)
    }

    return props.rosterInfo ?  
        (<table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Played</th>
                    <th>Won</th>
                    <th>Lost</th>
                    <th>KOs</th>
                    <th>Passive KOs</th>
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
            <td>{leagueStats.gamesWon}</td>
            <td>{leagueStats.gamesPlayed - leagueStats.gamesWon}</td>
            <td>{leagueStats.directKOs}</td>
            <td>{leagueStats.indirectKOs}</td>
            <td>{leagueStats.deaths}</td>
            <td>{leagueStats.directKOs + leagueStats.indirectKOs - leagueStats.deaths}</td>
        </tr>
    );
}

export default Summary;