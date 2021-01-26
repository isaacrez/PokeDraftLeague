import React from '../node_modules/react';
import Header from './components/Header';
import PokeTable from './components/PokeTable';
import 'bootstrap/dist/css/bootstrap.css';
import './App.css';

function App() {
  const [league, setLeague] = React.useState("");
  const [mainContent, setMainContent] = React.useState(<PokeTable />);

  return (
    <div className="App">
      <Header
        league={league}
        setLeague={setLeague}
        setMainContent={setMainContent} />
      {mainContent}
    </div>
  );
}

export default App;
