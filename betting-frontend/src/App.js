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
    <Router>
      <div className="App">
        <Header/>
        <NavBar/>
        <Switch>
            <Route path='/' exact={true} component={PlayTicket}/>
            <Route path='/tickets' exact={true} component={Tickets}/>
            <Route path='/transactions' exact={true} component={Transactions}/>
            <Route path='/addMoney' exact={true} component={AddMoney}/>
            <Route path='/logOut' exact={true} component={LogOut}/>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
