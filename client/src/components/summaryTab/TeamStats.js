import React from 'react';

function TeamStats(props) {
    function buildTeamStats() {
        const stats = new Map([
            ["Played", props.teamStats.gamesPlayed],
            ["Won", props.teamStats.gamesWon],
            ["Lost", props.teamStats.gamesPlayed - props.teamStats.gamesWon],
            ["Differential", props.teamStats.differential > 0 ? `+${props.teamStats.differential}` : props.teamStats.differential]
        ]);

        let output = [];
        stats.forEach((val, desc) => output.push(
            <div className="d-flex justify-content-between">
                <p>{desc}:</p>
                <p>{val}</p>
            </div>
        ));
        return output;
    }

    return (
        <div className="bubble my-3">
            {buildTeamStats()}
        </div>
    );
}

export default TeamStats;