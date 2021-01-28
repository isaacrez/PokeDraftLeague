import React from 'react';

import Header from './components/general/Header';
import Home from './components/homeTab/Home';
import Pokemon from './components/pokemonTab/Pokemon';

import 'bootstrap/dist/css/bootstrap.css';
import './App.css';


function App() {
  const [league, setLeague] = React.useState("");
  const [mainContent, setMainContent] = React.useState("Home");

  let stringToContent = {
    "Home": <Home setLeague={setLeague}/>,
    "Pokémon": <Pokemon />
  }

  return (
    <div className="App">
      <Header setMainContent={setMainContent} />
      {stringToContent[mainContent]}
    </div>
  );
}

export default App;
