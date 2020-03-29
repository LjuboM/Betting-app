import React, { Component } from 'react';

// first we will make a new context
const MyContext = React.createContext();

// Create an exportable consumer that can be injected into components
export const ExportableContext = MyContext.Consumer

// Then create a provider Component
class MyProvider extends Component {
    state = { 
      User : []
     }
  fetchUser = () => {
    fetch('/api/user/1', {})
    .then(body => body.json())
    .then(body => this.setState({User : body , isLoading: false}))
    .catch(error => console.log(error)); 
  };
  
  componentDidMount(){
    this.fetchUser()
  }
    render() { 
        return ( 
            <MyContext.Provider value={{
                state: this.state
                ,
                  increaseMoneyValue: (addedMoney) => {
                    let newUserState = this.state.User;
                    newUserState.money = parseInt(newUserState.money) + parseInt(addedMoney);
                    this.setState({User : newUserState});
                  }
              }}>
                {this.props.children}
              </MyContext.Provider>
         );
    }
}

export default MyProvider;