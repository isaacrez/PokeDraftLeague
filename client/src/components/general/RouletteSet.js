import React from 'react';
import Card from './Card';

function RouletteSet(props) {

    const [isExpanded, setIsExpanded] = React.useState(false);

    let cards = props.data.map(d => 
        <Card imgUrl={d.imgUrl}
            title={d.title}
            subtitle={d.subtitle}
            recolor={props.recolorIf(d)} />);

    let button = <button onClick={() => setIsExpanded(curr => !curr)}
                    className="btn btn-secondary">
                    {isExpanded ? "Show less" : "Show more"} </button>

    let classLabels = "d-flex roulette mb-1 ";
    classLabels += isExpanded ? "overflow-auto" : "overflow-auto";

    return (<div className="d-flex flex-column align-items-center">
        <div className={classLabels}>
            {cards}
        </div>
        {button}
    </div>);
}

export default RouletteSet;