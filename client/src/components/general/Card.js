import React from 'react';

function Card(props) {
    let recolor = props.recolor ? "taken" : null;
    return (<div className={`card mx-1 ${recolor}`}>
        <img src={props.imgUrl} alt={props.alt} />
        <p>
            {props.title}
            <br />
            {props.subtitle}
        </p>
    </div>)
}

export default Card;