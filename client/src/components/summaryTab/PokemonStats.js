import React from 'react';
import { addLabel } from '../../util/pokeEntry';

function PokemonStats(props) {

    const [pokeData, setPokeData] = React.useState([]);

    React.useEffect(() => {
        let pokemonUrl = `http://localhost:8080/api/pokemon/team/${props.team.id}/${props.league.id}`;
        fetch(pokemonUrl, {type: "GET"})
            .then(response => response.json())
            .then(pokemonData => setPokeData(pokemonData))
            .catch(error => console.log(error));
    }, [props.team.id, props.league.id]);

    function makeRows() {
        return pokeData.map(data => <Entry league={props.league} data={data} key={data.pokemon.id} />)
    }

    return (
        <div className="scrollable-table short">
            <table>
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
            </table>
        </div>
    );
}

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState({});
    const [loaded, setLoaded] = React.useState(false);
    const KD = leagueStats.directKOs + leagueStats.indirectKOs - leagueStats.deaths;
    const classKD = KD === 0 ? "neutral" : KD > 0 ? "good" : "bad";

    React.useEffect(() => {
        console.log(props);

        setLoaded(false);
        let url = `http://localhost:8080/api/pokemon/stats/${props.data.pokemon.id}/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .then(() => setLoaded(true))
            .catch(error => console.log(error));
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [props.data.pokemon.id]);


    return loaded ? (
        <tr key={props.data.pokemon.id}>
            {addLabel(props.data)}
            <td>{leagueStats.gamesPlayed}</td>
            <td>{leagueStats.directKOs}</td>
            <td>{leagueStats.indirectKOs}</td>
            <td>{leagueStats.deaths}</td>
            <td className={classKD}>{KD}</td>
        </tr>
    ) : null;
}

export default PokemonStats;