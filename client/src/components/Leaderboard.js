import React from 'react';

function Leaderboard(props) {

    const [teamResults, setTeamResults] = React.useState([]);

    React.useEffect(() => {
        let url = `http://localhost:8080/api/league/results/${props.league.id}`;
        fetch(url, {type: "GET"})
            .then(response => response.json())
            .then(data => setTeamResults(data));
    }, [props.league.id])

    function addDifferential(differential) {
        let label = differential > 0 ? "good" :
            differential < 0 ? "bad" : "neutral";
        let text = differential > 0 ? `+${differential}` : differential;
        return <td className={label}>{text}</td>
    }


    function generateRows() {
        return teamResults.sort((a, b) => a.gamesWon - b.gamesWon !== 0
                ? b.gamesWon - a.gamesWon
                : b.differential - a.differential)
            .map((v, i) =>
                <tr key={v.team.name}>
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
                    {addDifferential(v.differential)}
                </tr>
            )
    }

    return (
        <div className="full-stripe">
            <div className="scrollable-table very-tall">
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

export default Leaderboard;