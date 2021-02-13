import React from 'react';

function TeamSelector(props) {

    function makeTeamOptions() {
        let options = props.teams.map(team => <option key={team.id}>{team.name}</option>);
        options.unshift(<option key={props.NO_TEAM_SELECT} value={props.NO_TEAM_SELECT}>All</option>)
        return options;
    }

    return (
        <div className="minor-dropdown">
            <label htmlFor="team" className="mr-3">Team</label>
            <select id="team" onChange={e => props.setTeam(e.target.value)}>
                {makeTeamOptions()}
            </select>
        </div>
    );
}

export default TeamSelector;