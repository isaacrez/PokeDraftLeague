import React from 'react';
import LeagueSelector from './LeagueSelector';

function Home(props) {
    return(
        <div className="full-stripe">

            <img src={`${process.env.PUBLIC_URL}/img/icon.png`} alt="" />

            <LeagueSelector setLeague={props.setLeague}/>

        </div>
    )
}

export default Home;