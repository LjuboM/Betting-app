import React, { Component } from 'react';

// first we will make a new context
const MyContext = React.createContext();

// Create an exportable consumer that can be injected into components
export const ExportableContext = MyContext.Consumer

// Then create a provider Component
class MyProvider extends Component {
    state = { 
      User : [],
      NewTicket : [],
      totalOdd : 1,
      possibleGain : 0,
      money : 0
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
                    if(addedMoney >= 1 && addedMoney.toString().search(/\./) === -1 && addedMoney.toString().search(/e/) === -1){
                      let newUserState = this.state.User;
                      newUserState.money = parseInt(newUserState.money) + parseInt(addedMoney);
                      this.setState({User : newUserState});
                    }
                  },
                  addPair: (odd, type, Odds) => {
                    //check if pair already exists
                    //replace it if it exists
                    //if not add it:
                    let newTotalOdd = this.state.totalOdd * odd;
                    let newPossibleGain = this.state.money * newTotalOdd;
                    let ticketContruction = {
                      "odds": Odds,
                      "odd": odd,
                      "type": type
                    };
                      this.setState({ NewTicket: [...this.state.NewTicket, ticketContruction], totalOdd: newTotalOdd, possibleGain: newPossibleGain})
                  },
                  handleBetMoneyInput: (event) =>{
                    const target= event.target;
                    const newMoneyValue = target.value;
                    //if there are no pairs picked for bet, no need for calculating possible gain
                    //money input must be only integers higher than 1
                    if(this.state.NewTicket.length > 0 && newMoneyValue >= 1 && newMoneyValue.toString().search(/\./) === -1 && newMoneyValue.toString().search(/e/) === -1){
                      const newPossibleGainValue = newMoneyValue * this.state.totalOdd;
                      this.setState({possibleGain: newPossibleGainValue, money : newMoneyValue});
                    }
                  },
                  playTicket: () => {
                    //Calculate total odd
                    //calculate possibleGain
                    //money?


                    let ticketInfo = {
                      "totalodd": 1, //?
                      "possiblegain": 1, //?
                      "transaction": {
                          "money": 1, //?
                          "user": {
                            "id": 1
                          }
                      }
                    }

                  }
              }}>
                {this.props.children}
              </MyContext.Provider>
         );
    }
}

export default MyProvider;