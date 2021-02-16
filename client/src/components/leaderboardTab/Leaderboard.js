import React from 'react';

function Leaderboard(props) {
    return props.league.id === -1 ?
        <EmptyLeaderboard /> :
        <RegularLeaderboard league={props.league} />
}

function RegularLeaderboard(props) {
    return (
        <div className="full-stripe">
            <h1>Leaderboard</h1>
            <h2>{props.league.name}</h2>
            <table>
                <thead>
                    <tr>
                        <th>Place</th>
                        <th colSpan="2">Team</th>
                        <th>Differential</th>
                    </tr>
                </thead>
            </table>
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