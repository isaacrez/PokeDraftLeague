import React from '../node_modules/react';

import Header from './components/Header';
import Home from './components/Home';
import PokeTable from './components/PokeTable';

import 'bootstrap/dist/css/bootstrap.css';
import './App.css';


function App() {
  const [league, setLeague] = React.useState("");
  const [mainContent, setMainContent] = React.useState("Home");

  let stringToContent = {
    "Home": <Home setLeague={setLeague}/>,
    "PokeTable": <PokeTable />
  }

  return (
    <div className="App">
      <Header setMainContent={setMainContent} />
      {stringToContent[mainContent]}
    </div>
  );
}

export default App;
