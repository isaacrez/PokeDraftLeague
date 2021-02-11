import React from 'react';

function Schedule(props) {
    return props.league !== "" ?
        regularSchedule(props) : emptySchedule();
}

function regularSchedule(props) {
    return (
        <div className="full-stripe">
            <h1>Match Schedule</h1>
            <h2>{props.league}</h2>
            <table className="w-100 table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Week</th>
                        <th colSpan="2">Team</th>
                        <th colSpan="2">Team</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    )
}

function emptySchedule() {
    return (
        <div className="full-stripe">
            <h1>Match Schedule... is Missing!</h1>
            <h2>Select a league first!</h2>
            <p>You can select a league under the "Home" tab</p>
        </div>
    )
}

export default Schedule;