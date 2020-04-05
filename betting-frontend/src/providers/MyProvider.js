import React, { Component } from 'react';

// first we will make a new context
const MyContext = React.createContext();

// Create an exportable consumer that can be injected into components
export const ExportableContext = MyContext.Consumer

// Then create a provider Component
class MyProvider extends Component {

  constructor(props){
    super(props)
    this.state = { 
      User : [],
      NewTicket : [],
      totalOdd : 1,
      possibleGain : 0,
      money : '',
      isHidden: true,
      alertMessage : ''
     }
     this.createNewTicket= this.createNewTicket.bind(this);
     this.refreshTicket= this.refreshTicket.bind(this);
}

async createNewTicket(finalTicket){
  console.log(finalTicket);
  await fetch(`/api/ticket`, {
      method : 'POST',
      headers : {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body : JSON.stringify(finalTicket),
  });
  this.refreshTicket();
}

  refreshTicket(){
    this.setState({ NewTicket: [], totalOdd: 1, possibleGain: 0, money: '', isHidden : true, alertMessage : ''})
  }
  fetchUser = () => {
    fetch('/api/user/1', {})
    .then(body => body.json())
    .then(body => this.setState({User : body}))
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
                  changeMoneyValue: (newMoneyValue, addingMoney) => {
                    if(newMoneyValue >= 1 && newMoneyValue.toString().search(/\./) === -1 && newMoneyValue.toString().search(/e/) === -1){
                      let newUserState = this.state.User;
                      if(addingMoney){
                        newUserState.money = parseInt(newUserState.money) + parseInt(newMoneyValue);
                      }
                      else{
                        newUserState.money = parseInt(newUserState.money) - parseInt(newMoneyValue);
                      }
                      this.setState({User : newUserState});
                    }
                  },
                  modifyPair: (odd, type, Odds) => {
                    let finalNewTicket = this.state.NewTicket;
                    let newTotalOdd = this.state.totalOdd
                    let isAlertNotNeeded = this.state.isHidden;
                    let newAlertMessage = this.state.alertMessage;
                    //check if we already bet on that exat odd, if yes, remove it from NewTicket
                    let oddAlreadyExists = finalNewTicket.filter(pair => pair.odds.id === Odds.id && pair.type === type);

                    oddAlreadyExists.map( pair => {
                      newTotalOdd = newTotalOdd / pair.odd;
                      return pair;
                    })

                    if(oddAlreadyExists.length > 0){
                        let newMoneyValue = this.state.money;
                        let newPossibleGain = newMoneyValue * newTotalOdd;
                        finalNewTicket = this.state.NewTicket.filter(pair => pair.odds.id !== Odds.id);
                        if(newTotalOdd === 1.00){
                          newMoneyValue = '';
                          newPossibleGain = 0;
                        }
                        //check if constraint is no longer needed
                        if(this.state.alertMessage === 'With Special offer you have to combine 5 Basic offers with Odd >= 1.10!' && Odds.type === "Special offer"){
                          isAlertNotNeeded = true;
                        }
                        this.setState({ NewTicket: finalNewTicket, totalOdd: newTotalOdd, possibleGain: newPossibleGain, money: newMoneyValue, isHidden: isAlertNotNeeded, alertMessage : newAlertMessage})
                    }
                    
                    else{
                        newTotalOdd = newTotalOdd * odd;

                        //check if we already bet on that match
                        let pairAlreadyExists = finalNewTicket.filter(pair => pair.odds.match.id === Odds.match.id);

                        pairAlreadyExists.map( pair => {
                          newTotalOdd = newTotalOdd / pair.odd;
                          return pair;
                        })

                        if(pairAlreadyExists.length > 0){
                          finalNewTicket = [...this.state.NewTicket].filter(pair => pair.odds.match.id !== Odds.match.id);
                        }

                        //check if we already bet one special match
                        let specialMatchAlreadyExists = finalNewTicket.filter(pair => pair.odds.type === "Special offer" && Odds.type === "Special offer");                    
                        
                        specialMatchAlreadyExists.map( pair => {
                          console.log("already special match");
                          newTotalOdd = newTotalOdd / pair.odd;
                          return pair;
                        })

                        if(specialMatchAlreadyExists.length > 0){
                          finalNewTicket = [...this.state.NewTicket].filter(pair => pair.odds.match.id !== Odds.match.id).filter(pair => pair.odds.type !== "Special offer" && Odds.type === "Special offer");
                        }                    

                        //check if constraint is no longer needed
                        if( specialMatchAlreadyExists.length === 0 && this.state.alertMessage === 'With Special offer you have to combine 5 Basic offers with Odd >= 1.10!' && finalNewTicket.filter(pair => pair.odds.type === "Basic" && pair.odd >= 1.10).length >= 4 && odd >= 1.10){
                          isAlertNotNeeded = true;
                        }

                        const newPossibleGain = this.state.money * newTotalOdd;
                        const newPair = {
                          "odds": Odds,
                          "odd": odd,
                          "type": type
                        };
                          this.setState({ NewTicket: [...finalNewTicket, newPair], totalOdd: newTotalOdd, possibleGain: newPossibleGain, isHidden: isAlertNotNeeded })
                    }
                  },
                  handleBetMoneyInput: (event) =>{
                    const target= event.target;
                    const newMoneyValue = target.value;
                    //if there are no pairs picked for bet, no need for calculating possible gain
                    //money input must be only integers higher than 1
                    if(this.state.NewTicket.length > 0){
                      if(newMoneyValue >= 1 && newMoneyValue.toString().search(/\./) === -1 && newMoneyValue.toString().search(/e/) === -1){
                        const newPossibleGainValue = newMoneyValue * this.state.totalOdd;
                        this.setState({possibleGain: newPossibleGainValue, money : newMoneyValue, isHidden : true, alertMessage : ''});
                      }
                      else{
                        this.setState({isHidden : false, alertMessage : 'Only positive integer values higher than 1 are accepted!'});
                      }
                    }
                    else{
                      this.setState({isHidden : false, alertMessage : 'You have to add Matches for betting!'});
                    }
                  },
                  playTicket: () => {
                    let finalNewTicket = [];
                    const moneyValue =  this.state.money;

                    if(moneyValue < 1 || moneyValue.toString().search(/\./) !== -1 || moneyValue.toString().search(/e/) !== -1 || moneyValue === ''){
                      this.setState({isHidden : false, alertMessage : 'Only positive integer values higher than 1 are accepted!'});
                    }
                    else if( this.state.NewTicket.filter(pair => pair.odds.type === "Special offer").length === 1 && this.state.NewTicket.filter(pair => pair.odds.type === "Basic" && pair.odd >= 1.10).length < 5){
                      this.setState({isHidden : false, alertMessage : 'With Special offer you have to combine 5 Basic offers with Odd >= 1.10!'});
                    }
                    else{
                      this.state.NewTicket.map( ticketOdd => {
                        finalNewTicket = [...finalNewTicket, 
                          ticketOdd = {
                          "ticket": {
                            "totalodd": this.state.totalOdd,
                            "possiblegain": this.state.possibleGain,
                            "transaction": {
                                "money": this.state.money,
                                    "user": {
                                    "id": 1
                                }
                            }
                          },
                          "odds": ticketOdd.odds,
                          "odd": ticketOdd.odd,
                          "type": ticketOdd.type
                          }]
                      return null;
                    })
                      this.createNewTicket(finalNewTicket);
                    }
                  },
                  isPairSelected: (oddId) => {
                    return this.state.NewTicket.some(pair => pair.odds.id === oddId);
                  },
                  isOddTypeOfPairSelected: (oddId, oddTypeValue) => {
                    return this.state.NewTicket.some(pair => pair.odds.id === oddId && pair.type === oddTypeValue);
                  },
                  deleteNewTicket: () =>{
                    this.refreshTicket();
                  },
                  deletePair: (oddId, odd) => {
                    let isAlertNotNeeded = this.state.isHidden;
                    let newAlertMessage = this.state.alertMessage;
                    let newPossibleGain = 0;
                    let newMoneyValue = this.state.money;
                    const newTotalOdd = this.state.totalOdd / odd;
                    newPossibleGain = this.state.money * newTotalOdd;
                    if(newTotalOdd === 1.00){
                      newPossibleGain = 0;
                      newMoneyValue = '';
                    }
                    //check if constraint is no longer needed
                    if(this.state.alertMessage === 'With Special offer you have to combine 5 Basic offers with Odd >= 1.10!' && this.state.NewTicket.filter(pair => pair.odds.id === oddId && pair.odds.type === "Special offer").length >= 1){
                      newAlertMessage = '';
                      isAlertNotNeeded = true;
                    }

                    const finalNewTicket = this.state.NewTicket.filter(pair => pair.odds.id !== oddId);
                    this.setState({ NewTicket: finalNewTicket, totalOdd: newTotalOdd, possibleGain: newPossibleGain, money: newMoneyValue, isHidden: isAlertNotNeeded, alertMessage : newAlertMessage})
                  }
              }}>
                {this.props.children}
              </MyContext.Provider>
         );
    }
}

export default MyProvider;