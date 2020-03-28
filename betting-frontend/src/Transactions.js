import React, { Component } from 'react';
import { Table } from 'reactstrap';

class Transactions extends Component {
    state = { 
        isLoading : true,
        Transactions : []
     }

     fetchTransactions = () => {
        fetch('/api/transactions', {})
        .then(body => body.json())
        .then(body => {
            let modifiedBody = body;
            modifiedBody.map( transaction => {
                if(transaction.transactiontype === true){
                    transaction.transactiontype="Ticket";
                    transaction.money = "-" + transaction.money;
                }
                else{
                    transaction.transactiontype="Added money";
                }
                return transaction;
            })
            return modifiedBody;
        })
          .then(body => this.setState({Transactions : body , isLoading: false}))
          .catch(error => console.log(error)); 
      };

      componentDidMount(){
        this.fetchTransactions()
    }

    render() {
        const {Transactions , isLoading} = this.state;
        if(isLoading) 
            return (<div>Loading...</div>);

        return ( 
            <Table striped >
            <thead>
              <tr>
                <th>#</th>
                <th>Date</th>
                <th>Type</th>
                <th>Money</th>
              </tr>
            </thead>
            {
            Transactions.map( transaction =>
                <tbody key={transaction.id} className={transaction.transactiontype}>
                    <tr>
                        <th scope="row" >{transaction.id}</th>
                        <td>{transaction.transactiondate}</td>
                        <td>{transaction.transactiontype}</td>
                        <td>{transaction.money}</td>
                    </tr>
                </tbody>
            )
            }
          </Table>
        );
    }
}
 
export default Transactions;