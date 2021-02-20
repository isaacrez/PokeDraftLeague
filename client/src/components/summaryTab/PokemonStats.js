import React from 'react';
import {cleanText} from '../../util/pokeEntry';

function PokemonStats(props) {

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
    const KD = leagueStats.directKOs + leagueStats.indirectKOs - leagueStats.deaths;
    const classKD = KD === 0 ? "neutral" : KD > 0 ? "good" : "bad";

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
            <td className={classKD}>{KD}</td>
        </tr>
    );
}

export default PokemonStats;