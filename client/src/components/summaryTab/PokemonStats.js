import React from 'react';
import { addLabel } from '../../util/pokeEntry';

function PokemonStats(props) {

    const [pokeData, setPokeData] = React.useState([]);
    const headerLabels = ["Name", "Tier", "Played", "KOs", "Passive", "Deaths", "+/-"];

    const rows = pokeData.sort((a, b) => a.tier - b.tier)
                .map(data => <Entry league={props.league} data={data} key={data.pokemon.id} />);
    const header = headerLabels.map(s => <th key={s}>{s}</th>);

    React.useEffect(() => {
        let pokemonUrl = `http://localhost:8080/api/pokemon/team?teamId=${props.team.id}&leagueId=${props.league.id}`;
        fetch(pokemonUrl, {type: "GET"})
            .then(response => response.json())
            .then(pokemonData => setPokeData(pokemonData))
            .catch(error => console.log(error));
    }, [props.team.id, props.league.id]);

    return (
        <div className="scrollable-table short">
            <table>
                <thead>
                    <tr>
                        {header}
                    </tr>
                </thead>
                <tbody>
                    {rows}
                </tbody>
            </table>
        </div>
    );
}

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState({});
    const content = [props.data.tier, leagueStats.gamesPlayed, leagueStats.directKOs,
        leagueStats.indirectKOs, leagueStats.deaths];

    const KD = leagueStats.directKOs + leagueStats.indirectKOs - leagueStats.deaths || 0;
    const classKD = KD === 0 ? "neutral" : KD > 0 ? "good" : "bad";

    const cells = [
        addLabel(props.data),
        content.map((s, i) => <td key={i}>{s}</td>),
        <td className={classKD} key={"kd"}>{KD > 0 ? `+${KD}` : KD}</td>
    ];

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/stats?pokeId=${props.data.pokemon.id}&leagueId=${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }, [props.data.pokemon.id, props.league.id]);

    return (<tr key={props.data.pokemon.id}>
        {cells}
    </tr>);
}

export default PokemonStats;