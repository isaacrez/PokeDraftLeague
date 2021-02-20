import React from 'react';

function TableBody(props) {

    function isMatchValidWeek(match) {
        return Number(props.week) === props.NO_WEEK_SELECT
            || match.scheduledWeek === Number(props.week);
    }

    function isTeamInMatch(match, teamName) {
        return props.team === props.NO_TEAM_SELECT
            || match.teams[0].name === teamName
            || match.teams[1].name === teamName;
    }

    function makeTableContent() {
        return props.matches
            .filter(match => isMatchValidWeek(match))
            .filter(match => isTeamInMatch(match, props.team))
            .map(match => 
                <tr key={`${match.teams[0].acronym} vs ${match.teams[1].acronym}`}>
                    <td><h3 className="fancy">{match.scheduledWeek}</h3></td>
                    <td>
                        <img src={`${process.env.PUBLIC_URL}/img/logos/${match.teams[0].acronym}.png`}
                            className="lg-icon"
                            alt={`${match.teams[0].acronym} team logo`} />
                        <h4>{match.teams[0].name}</h4>
                    </td>
                    <td>
                        <img src={`${process.env.PUBLIC_URL}/img/logos/${match.teams[1].acronym}.png`}
                            className="lg-icon" 
                            alt={`${match.teams[1].acronym} team logo`} />
                        <h4>{match.teams[1].name}</h4>
                    </td>
                </tr>
            );
    }

    return (
        <div className="scrollable-table">
            <table className="hoverable">
                <thead>
                    <tr>
                        <th>Week</th>
                        <th>Team</th>
                        <th>Team</th>
                    </tr>
                </thead>
                <tbody>
                    {makeTableContent()}
                </tbody>
            </table>
        </div>
    );
}

export default TableBody;