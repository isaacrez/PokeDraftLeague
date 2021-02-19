import React from 'react';

function Leaderboard(props) {
    return props.league.id === -1 ?
        <EmptyLeaderboard /> :
        <RegularLeaderboard league={props.league} />
}

function RegularLeaderboard(props) {

    const [teamResults, setTeamResults] = React.useState([]);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/results/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => setTeamResults(data));
    }, [])


    function generateRows() {
        return teamResults.sort((a, b) => a.gamesWon - b.gamesWon !== 0
                ? b.gamesWon - a.gamesWon
                : b.differential - a.differential)
            .map((v, i) =>
                <tr>
                    <td className="fancy">{i + 1}</td>
                    <td>
                        <img src={`${process.env.PUBLIC_URL}/img/logos/${v.team.acronym}.png`}
                            className="icon"
                            alt={`${v.team.acronym}'s logo`} />
                    </td>
                    <td>{v.team.name}</td>
                    <td>{v.gamesPlayed}</td>
                    <td>{v.gamesWon}</td>
                    <td>{v.gamesPlayed - v.gamesWon}</td>
                    <td>{v.differential}</td>
                </tr>
            )
    }

    return (
        <div className="full-stripe">
            <h1>Leaderboard</h1>
            <h2>{props.league.name}</h2>
            <div className="scrollable-table">
                <table>
                    <thead>
                        <tr>
                            <th>Place</th>
                            <th colSpan="2">Team</th>
                            <th>Played</th>
                            <th>Won</th>
                            <th>Lost</th>
                            <th>Differential</th>
                        </tr>
                    </thead>
                    <tbody>
                        {generateRows()}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

function EmptyLeaderboard() {
    return (
        <div className="full-stripe">
            <h1>Leaderboard... is Missing!</h1>
            <h2>No league selected</h2>
            <p>
                To view a leaderboard, you need to select a league 
                from the "Home" tab first!
            </p>
        </div>
    )
}

export default Leaderboard;