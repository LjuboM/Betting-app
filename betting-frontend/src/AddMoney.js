import React, { Component } from 'react';
import { InputGroup, InputGroupAddon, Input } from 'reactstrap';
import { Button, Alert } from 'reactstrap';
import { ExportableContext } from './providers/MyProvider';

class AddMoney extends Component {
    constructor(props){
        super(props)
  
        this.state = { 
          Transaction : this.emptyTransaction,
          isHidden: true
         }
         this.handleAddingMoney= this.handleAddingMoney.bind(this);
         this.handleMoneyInput= this.handleMoneyInput.bind(this);
         this.handleKeyDown= this.handleKeyDown.bind(this);
    }
    emptyTransaction = {
        "type": false,
        "money": 0,
        "user": { "id": 1 }
    }

    async handleAddingMoney(){
        const transaction = this.state.Transaction;
        if(transaction.money >= 1 && transaction.money.toString().search(/\./) === -1 && transaction.money.toString().search(/e/) === -1){
            await fetch(`/api/transaction`, {
                method : 'POST',
                headers : {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
                },
                body : JSON.stringify(transaction),
              });
            this.props.history.push("/transactions");
        }
        else{
            this.setState({
                isHidden: false
            })
        }
    }

    handleMoneyInput(event){
        const target= event.target;
        const money= target.value;
        let transaction={...this.state.Transaction};
        transaction.money = money;
        this.setState({Transaction: transaction});
    }

    handleKeyDown(event){
        if (event.key === 'Enter') {
            this.handleAddingMoney();
        }
    }
    render() {
      const {Transaction} = this.state;
        return (
        <div>
          <ExportableContext>
            {(value) => (
              <InputGroup style={{margin:"15px", width:"50%"}}>
                <InputGroupAddon addonType="prepend">HRK</InputGroupAddon>
                <Input placeholder="Amount" min={1} type="number" step="1" onChange={this.handleMoneyInput} onKeyDown={this.handleKeyDown}/>
                <InputGroupAddon addonType="append"><Button color="primary" onClick={() => { this.handleAddingMoney(); value.increaseMoneyValue(Transaction.money);}}>Add Money</Button></InputGroupAddon>
              </InputGroup>
            )}
          </ExportableContext>
          {!this.state.isHidden && 
          <Alert color="danger">
            Only positive integer values higher than 1 are accepted!
          </Alert>}

        </div>
        );
    }
}
 
export default AddMoney;