import React from 'react';
import TeamStats from './TeamStats';

function SummaryHeader(props) {
    return (
        <div className="w-100 d-flex justify-content-around align-items-center">
            <img src={`${process.env.PUBLIC_URL}/img/logos/${props.team.acronym}.png`}
                className="xl-icon" />

            <div className="d-flex flex-column align-items-center">
                <h1>{props.team.name}</h1>
                <TeamStats teamStats={props.teamStats} />
            </div>
        </div>
    )
}

export default SummaryHeader;