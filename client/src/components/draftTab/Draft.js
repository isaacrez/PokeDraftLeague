import React from 'react';

function Draft(props) {
    return (
        <div className="full-stripe">
            <Card imgUrl="https://www.serebii.net/swordshield/pokemon/001.png"
                title="Bulbasaur"
                subtitle="FREE" />
            <Card imgUrl="https://www.serebii.net/swordshield/pokemon/003.png"
                title="Venusaur"
                subtitle="LSL" />
        </div>
    )
}

function Card(props) {
    return (<div className="card">
        <img src={props.imgUrl} />
        <p>{props.title}</p>
        <p>{props.subtitle}</p>
    </div>)
}

export default Draft;