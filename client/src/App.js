import React from 'react';

import Header from './components/Header';
import Home from './components/Home';
import Leaderboard from './components/Leaderboard';
import Schedule from './components/Schedule';
import Summary from './components/Summary';
import Match from './components/Match';
import Draft from './components/Draft';
import Pokemon from './components/Pokemon';

import 'bootstrap/dist/css/bootstrap.css';
import './App.css';


function App() {

  const [league, setLeague] = React.useState({name: "", id: -1});
  const accessiblePages = [{
    label: "Home",
    output: <Home setLeague={setLeague} />,
    alwaysAccessible: true
  },
  {
    label: "Leaderboard",
    output: <Leaderboard league={league} />,
    alwaysAccessible: false
  },
  {
    label: "Schedule",
    output: <Schedule league={league}/>,
    alwaysAccessible: false
  },
  {
    label: "Team Summary",
    output: <Summary league={league} />,
    alwaysAccessible: false
  },
  {
    label: "Match",
    output: <Match league={league} />,
    alwaysAccessible: false
  },
  {
    label: "Draft",
    output: <Draft league={league} />,
    alwaysAccessible: false
  },
  {
    label: "Pokémon",
    output: <Pokemon league={league}/>,
    alwaysAccessible: true
  }]

  const [mainContent, setMainContent] = React.useState(
    accessiblePages.find(o => o.label === "Home").output
  );

  return (
    <div className="App">
      <Header accessiblePages={accessiblePages}
        mainContent={mainContent}
        setMainContent={setMainContent}
        league={league} />
      {mainContent}
    </div>
  );
}

export default App;
