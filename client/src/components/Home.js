import React from '../../node_modules/react';
import LeagueSelector from './LeagueSelector';

function Home(props) {
    return(
        <div className="full-stripe">
            <h1>Welcome!</h1>

            <p>
                To get started, select your league from
                the dropdown menu below
            </p>

            <LeagueSelector setLeague={props.setLeague}/>

            <p>
                Then, access relevant information about 
                your league using the tabs above
            </p>

            <div className="h-25"></div>
        </div>
    )
}

export default Home;