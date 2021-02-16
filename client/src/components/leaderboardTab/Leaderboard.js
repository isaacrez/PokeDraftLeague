import React from 'react';

function Leaderboard(props) {
    return props.league.id === -1 ?
        <EmptyLeaderboard /> :
        <RegularLeaderboard league={props.league} />
}

function RegularLeaderboard(props) {

    const sampleData = [
        {
            team: {
                name: "San Antonio Swamperts",
                acronym: "SAS"
            },
            results: {
                won: 10,
                lost: 2,
                differential: 12
            }
        },
        {
            team: {
                name: "Brooklyn Beedrills",
                acronym: "BrB"
            },
            results: {
                won: 10,
                lost: 2,
                differential: 10
            }
        },
        {
            team: {
                name: "Buffalo Buzzwoles",
                acronym: "BBW"
            },
            results: {
                won: 11,
                lost: 1,
                differential: 30
            }
        }
    ]

    function generateRows() {
        return sampleData.sort((a, b) => a.results.won - b.results.won !== 0
                ? b.results.won - a.results.won
                : b.results.differential - a.results.differential)
            .map((v, i) =>
                <tr>
                    <td className="fancy">{i + 1}</td>
                    <td>
                        <img src={`${process.env.PUBLIC_URL}/img/logos/${v.team.acronym}.png`}
                            className="icon"
                            alt={`${v.team.acronym}'s logo`} />
                    </td>
                    <td>{v.team.name}</td>
                    <td>{v.results.won + v.results.lost}</td>
                    <td>{v.results.won}</td>
                    <td>{v.results.lost}</td>
                    <td>{v.results.differential}</td>
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