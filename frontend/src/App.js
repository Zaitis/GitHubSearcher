import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Form from './components/Form';
import NotFound from './components/NotFound';
import UserRepositoryList from './components/UserRepositoryList';

function App() {
  return (
    <Router>
      <Switch>
        <Route path="/" exact component={Form} />
        <Route path="/repositories/:username" component={UserRepositoryList} />
        <Route component={NotFound} />
      </Switch>
    </Router>
  );
}

export default App;