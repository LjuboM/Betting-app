import React, { Component } from 'react';
import { Table } from 'reactstrap';

class Transactions extends Component {
    state = { 
        isLoading : true,
        Transactions : []
     }
     async componentDidMount(){
        const response=await fetch('/api/transactions');
        const body = await response.json();
        for (const transaction of body){
            if(transaction.transactiontype === true){
            transaction.transactiontype="Ticket";
            transaction.money = "-" + transaction.money;
            }
            else{
                transaction.transactiontype="Added money";
            }
        }
        this.setState({Transactions : body , isLoading: false});

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