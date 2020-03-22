import React, { Component } from 'react';
import './App.css';
import { Route, BrowserRouter as Router,Switch} from 'react-router-dom'
import Header from './Header';
import NavBar from './NavBar';
import PlayTicket from './PlayTicket';
import Tickets from './Tickets';
import Transactions from './Transactions';
import AddMoney from './AddMoney';
import LogOut from './LogOut';


class App extends Component {
  constructor(props){
    super(props)

    this.state = { 
      User : []
     }
     this.increaseMoneyValue= this.increaseMoneyValue.bind(this);
}

  async componentDidMount(){
    const response = await fetch('/api/user/1');
    const body = await response.json();
    this.setState({User : body});
  }

  increaseMoneyValue(addedMoney){
    let newUserState = this.state.User;
    newUserState.money = parseInt(newUserState.money) + parseInt(addedMoney);
    this.setState({User : newUserState});
  }

  render() { 
    return ( 
      <Router>
      <div className="App">
        <Header userName={this.state.User.name} moneyInWallet={this.state.User.money}/>
        <NavBar/>
        <Switch>
            <Route path='/' exact={true} component={PlayTicket}/>
            <Route path='/tickets' exact={true} component={Tickets}/>
            <Route path='/transactions' exact={true} component={Transactions}/>
            <Route path='/addMoney' exact={true} render={(props) => <AddMoney {...props} increaseMoney={this.increaseMoneyValue}/>} />
            <Route path='/logOut' exact={true} component={LogOut}/>
        </Switch>
      </div>
    </Router>
     );
  }
}
 
export default App;