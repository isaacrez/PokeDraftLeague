import React from 'react';

function Draft(props) {
    return (
        <div className="full-stripe">
            <div className="w-100 d-flex mx-3 flex-wrap">
                <Card imgUrl="https://www.serebii.net/swordshield/pokemon/001.png"
                    title="Bulbasaur"
                    subtitle="FREE" />
                <Card imgUrl="https://www.serebii.net/swordshield/pokemon/003.png"
                    title="Venusaur"
                    subtitle="LSL" />
            </div>
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