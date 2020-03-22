import React from 'react';
import './App.css';
import { Route, BrowserRouter as Router,Switch} from 'react-router-dom'
import Header from './Header';
import NavBar from './NavBar';
import PlayTicket from './PlayTicket';
import Tickets from './Tickets';
import Transactions from './Transactions';
import AddMoney from './AddMoney';
import LogOut from './LogOut';

function App() {
  return (
    <div className="App">
      <Header/>
      <NavBar/>
      <Router>
          <Switch>
              <Route path='/' component={PlayTicket}/>
              <Route path='/tickets' component={Tickets}/>
              <Route path='/transactions' component={Transactions}/>
              <Route path='/addMoney' component={AddMoney}/>
              <Route path='/logOut' component={LogOut}/>
          </Switch>
      </Router>
    </div>
  );
}

export default App;
