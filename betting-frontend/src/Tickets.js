import React, { Component } from 'react';
import { Table } from 'reactstrap';

class Tickets extends Component {
    constructor(props){
        super(props)

        this.state = { 
            isLoading : true,
            Tickets : []
         }
    }
    

     async componentDidMount(){
        const ticketsResponse = await fetch('/api/tickets');
        const ticketsBody = await ticketsResponse.json();

        const ticketOddsResponse = await fetch('/api/ticketOdds');
        const ticketOddsBody = await ticketOddsResponse.json();

        for (const ticket of ticketsBody){
            let temporaryTicketOdds = [];
            for (const ticketOdd of ticketOddsBody){
                if( ticket.id === ticketOdd.ticket.id){
                    temporaryTicketOdds = temporaryTicketOdds.concat(
                        {
                            id : ticketOdd.id,
                            odd : ticketOdd.odd,
                            type : ticketOdd.type,
                            home : ticketOdd.odds.match.home,
                            away : ticketOdd.odds.match.away,
                            matchTime : ticketOdd.odds.match.matchdate,
                        }
                    );
                }
            }
            ticket.ticketOdds = temporaryTicketOdds;
        }
        this.setState({Tickets : ticketsBody , isLoading: false});
    }


    render() {
        const {Tickets, isLoading} = this.state;

        if(isLoading) 
            return (<div>Loading...</div>);

        return ( 
            <div>
            {
            Tickets.map( ticket =>
                <Table key={ticket.id}>
                    <thead style={{backgroundColor:"#dae0e5a1", fontweight:"normal"}}>
                        <tr style ={{fontweight:"normal"}}>
                            <th style ={{fontweight:"normal"}}># {ticket.id}</th>
                            <th>Total Odd: {ticket.totalodd}</th>
                            <th>Possible Gain: {ticket.possiblegain}</th>
                            <th>Date: {ticket.transaction.transactiondate}</th>
                            <th>Bet: {ticket.transaction.money} HRK</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style={{backgroundColor:"#dae0e5a1"}}>
                            <th></th>
                            <th>Home</th>
                            <th>Away</th>
                            <th>Match Date</th>
                            <th>Type</th>
                            <th>Odd</th>
                        </tr>
                    {ticket.ticketOdds.map(ticketOdd =>
                        <tr key={ticketOdd.id}>
                            <th scope="row" ></th>
                            <td>{ticketOdd.home}</td>
                            <td>{ticketOdd.away}</td>
                            <td>{ticketOdd.matchTime}</td>
                            <td>{ticketOdd.type}</td>
                            <td>{ticketOdd.odd}</td>
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
