import React, { Component } from 'react';

import { BrowserRouter as Router, NavLink } from 'react-router-dom'
import 'bootstrap/dist/css/bootstrap.css';

class NavBar extends Component {
    state = {  }
    render() {
        return (
          <Router>
              <ul>
                <li><NavLink exact activeClassName="active" to="/">Play Ticket</NavLink></li>
                <li><NavLink activeClassName="active" to="/tickets">Tickets</NavLink></li>
                <li><NavLink activeClassName="active" to="/transactions">Transactions</NavLink></li>
                <li><NavLink activeClassName="active" to="/addMoney">Add money</NavLink></li>
                <li style={{float:"right"}}><NavLink  activeClassName="active" to="/logOut">Log out</NavLink></li>
              </ul>
          </Router>
         );
    }
}
 
export default NavBar;