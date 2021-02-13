import React from 'react';

function TableBody(props) {

    function isMatchValidWeek(match) {
        return props.week === props.NO_WEEK_SELECT
            || match.scheduledWeek === props.week;
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
                <tr>
                    <td>{match.scheduledWeek}</td>
                    <td><img src={`${process.env.PUBLIC_URL}/img/logos/${match.teams[0].acronym}.png`}
                            className="lg-icon" /></td>
                    <td>{match.teams[0].name}</td>
                    <td><img src={`${process.env.PUBLIC_URL}/img/logos/${match.teams[1].acronym}.png`}
                            className="lg-icon" /></td>
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
                        <th colSpan="2">Team</th>
                        <th colSpan="2">Team</th>
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