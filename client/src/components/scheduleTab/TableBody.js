import React from 'react';

function TableBody(props) {

    function isMatchValidWeek(match) {
        return props.week === props.NO_WEEK_SELECT
            || match.scheduledWeek === props.weeks;
    }

    function isTeamInMatch(match, teamName) {
        return props.team === props.NO_TEAM_SELECT
            || match.teams[0].name == teamName
            || match.teams[1].name == teamName;
    }

    function makeTableContent() {
        return props.matches
            .filter(match => isMatchValidWeek(match))
            .filter(match => isTeamInMatch(match, props.team))
            .map(match => 
                <tr>
                    <td>{match.scheduledWeek}</td>
                    <td>{match.teams[0].name}</td>
                    <td>{match.teams[1].name}</td>
                </tr>
            );
    }

    return (
        <div className="scrollable-table">
            <table className="table table-secondary table-custom table-striped table-hover mb-0">
                <thead className="thead-dark">
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