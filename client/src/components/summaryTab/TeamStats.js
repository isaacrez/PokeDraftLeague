import React from 'react';

function TeamStats(props) {

    const statsData = [
        {label: "Played:", value: props.teamStats.gamesPlayed},
        {label: "Won:", value: props.teamStats.gamesWon},
        {label: "Lost:", value: props.teamStats.gamesPlayed - props.teamStats.gamesWon},
        {label: "Differential:", value: props.teamStats.differential > 0 ? `+${props.teamStats.differential}` : props.teamStats.differential}
    ]

    const stats = statsData.map(o => 
        <div className="d-flex justify-content-between" key={o.label}>
            <p>{o.label}</p>
            <p>{o.value}</p>
        </div>);

    return (<div className="bubble my-3">
            {stats}
        </div>);
}

export default TeamStats;