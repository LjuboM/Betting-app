import React, { Component } from 'react';
import { InputGroup, InputGroupAddon, Input } from 'reactstrap';
import { Button } from 'reactstrap';


class AddMoney extends Component {
    constructor(props){
        super(props)
  
        this.state = { 
          transaction : this.emptyTransaction
         }
         this.handleAddingMoney= this.handleAddingMoney.bind(this);
         this.handleMoneyInput= this.handleMoneyInput.bind(this);
    }
    emptyTransaction = {
        "type": false,
        "money": 0,
        "user": { "id": 1 }
    }

    async handleAddingMoney(event){
        const transaction = this.state.transaction;
        if(transaction.money >= 1 && transaction.money.toString().search(/\./) === -1){
            await fetch(`/api/transaction`, {
                method : 'POST',
                headers : {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body : JSON.stringify(transaction),
              });
            this.props.history.push("/transactions");
            this.props.increaseMoney(transaction.money);

        }
        else{
            console.log("You have to use positive integer values!");
        }
    }

    handleMoneyInput(event){
        const target= event.target;
        const money= target.value;
        let transaction={...this.state.transaction};
        transaction.money = money;
        this.setState({transaction});
    }

    render() { 
        return ( 
        <InputGroup style={{margin:"15px", width:"50%"}}>
            <InputGroupAddon addonType="prepend">HRK</InputGroupAddon>
            <Input placeholder="Amount" min={1} type="number" step="1" onChange={this.handleMoneyInput}/>
            <InputGroupAddon addonType="append"><Button color="primary" onClick={this.handleAddingMoney}>Add Money</Button></InputGroupAddon>
          </InputGroup>
        );
    }
}
 
export default AddMoney;