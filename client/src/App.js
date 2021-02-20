import React from 'react';

import Header from './components/Header';
import Home from './components/homeTab/Home';
import Leaderboard from './components/leaderboardTab/Leaderboard';
import Schedule from './components/scheduleTab/Schedule';
import Summary from './components/summaryTab/Summary';
import Pokemon from './components/pokemonTab/Pokemon';

import 'bootstrap/dist/css/bootstrap.css';
import './App.css';


function App() {
  const [league, setLeague] = React.useState({name: "", id: -1});
  const [mainContent, setMainContent] = React.useState("Home");

  let stringToContent = {
    "Home": <Home setLeague={setLeague}/>,
    "Leaderboard": <Leaderboard league={league} />,
    "Schedule": <Schedule league={league}/>,
    "Team Summary": <Summary league={league} />,
    "Pokémon": <Pokemon league={league}/>
  }

  return (
    <div className="App">
      <Header mainContent={mainContent}
        setMainContent={setMainContent}
        league={league} />
      {stringToContent[mainContent]}
    </div>
  );
}

export default App;
