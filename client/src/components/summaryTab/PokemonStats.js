import React from 'react';
import Entry from './Entry';

function PokemonStats(props) {

    const [pokeData, setPokeData] = React.useState([]);
    const headerLabels = ["Name", "Tier", "Played", "KOs", "Passive", "Deaths", "+/-"];

    console.log(pokeData);

    const rows = pokeData.sort((a, b) => a.tier - b.tier)
                .map(data => <Entry league={props.league} data={data} key={data.id} />);
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

export default PokemonStats;