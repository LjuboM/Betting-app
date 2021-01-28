import React, { Component } from 'react';
import { Table } from 'reactstrap';

class Tickets extends Component {
    constructor(props){
        super(props)

        this.state = { 
            isLoading : true,
            Transactions : [],
            TicketOdds : []
         }
         this.showPairs= this.showPairs.bind(this);
    }
    
    //Fetch all played tickets, 1-> transaction type of played ticket.
    fetchTransactionsOfPlayedTicket = () => {
        fetch('/api/transactions/1', {})
        .then(body => body.json())
          .then(body => this.setState({Transactions : body , isLoading: false}))
          .catch(error => console.log(error)); 
      };

    componentDidMount(){
        this.fetchTransactionsOfPlayedTicket()
    }

    showPairs(ticketId){
        const ticketViewOpened = this.state.TicketOdds.some(ticketOdd => ticketOdd.ticket.id === ticketId);
        if(ticketViewOpened){
            console.log("uslo")
            let updatedTicketOdds = [...this.state.TicketOdds].filter(ticketOdd => ticketOdd.ticket.id !== ticketId);
            this.setState({TicketOdds : updatedTicketOdds});
            console.log(this.state.TicketOdds)

        }
        else{
            fetch('/api/ticketOdds/' + ticketId, {})
            .then(body => body.json())
            .then(body => {
                const modifiedBody = this.state.TicketOdds.concat(body);
                return modifiedBody;
            })
              .then(body => this.setState({TicketOdds : body , isLoading: false}))
              .catch(error => console.log(error)); 
        }
    }

    render() {
        const {Transactions, TicketOdds, isLoading} = this.state;

        if(isLoading) 
            return (<div>Loading...</div>);

        return ( 
            <div>
            {
            Transactions.map( transactionInfo =>
                <Table key={transactionInfo.ticket.id}>
                    <thead className={"ticketView"} onClick={this.showPairs.bind(this, transactionInfo.ticket.id)}>
                        <tr>
                            <td className={"noselect"}><b># {transactionInfo.ticket.id}</b></td>
                            <td className={"noselect"}><b>{transactionInfo.transactiondate}</b></td>
                            <td className={"noselect"}></td>
                            <td className={"noselect"}>Possible Gain: <b>{transactionInfo.ticket.possiblegain} HRK</b></td>
                            <td className={"noselect"}>Bet: <b>{transactionInfo.money} HRK</b></td>
                            <td className={"noselect"}>Total Odd: <b>{transactionInfo.ticket.totalodd}</b></td>
                        </tr>
                    </thead>
                    <tbody>
                    {TicketOdds.filter(ticketOdd => ticketOdd.ticket.id === transactionInfo.ticket.id).map( ticketOddWithSportType =>
                        <tr key={ticketOddWithSportType.id}>
                            <th scope="row" ></th>
                            <td>{ticketOddWithSportType.odds.match.home}</td>
                            <td>{ticketOddWithSportType.odds.match.away}</td>
                            <td>{ticketOddWithSportType.odds.match.matchdate}</td>
                            <td>{ticketOddWithSportType.type}</td>
                            <td>{ticketOddWithSportType.odd}</td>
                        </tr>
                        )}
                    </tbody>
                </Table>
            )
            }
            </div>
        );
    }
}
 
export default Tickets;
