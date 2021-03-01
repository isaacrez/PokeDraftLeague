import React from 'react';
import LoadedEntry from './LoadedEntry';

function Entry(props) {

    const [leagueStats, setLeagueStats] = React.useState(null);
    const loaded = leagueStats !== null;

    React.useEffect(() => {
        let url = `http://localhost:8080/api/pokemon/stats?pokeId=${props.data.id}
                    &leagueId=${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(stats => setLeagueStats(stats))
            .catch(error => console.log(error));
    }, [props.data, props.league.id]);

    return loaded ?
        <LoadedEntry display={props.display} data={props.data} leagueStats={leagueStats} />
        : null;
}

export default Entry;