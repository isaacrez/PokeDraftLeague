import React from 'react';


function WeekSelector(props) {

    function makeWeekOptions() {
        let options = new Set(props.matches.map(match => match.scheduledWeek));
        options = [...options].map(o => <option key={o}>{o}</option>);
        options.unshift(<option key={props.NO_WEEK_SELECT} value={props.NO_WEEK_SELECT}>All</option>);
        return options;
    }

    return (
        <div className="d-flex minor-dropdown w-25 justify-content-around mb-3">
            <label htmlFor="week">Week</label>
            <select id="week" onChange={e => props.setWeek(Number(e.target.value))}>
                {makeWeekOptions()}
            </select>
        </div>
    );
}

export default WeekSelector;