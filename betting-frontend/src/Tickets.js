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
    
    fetchTickets = () => {
        fetch('/api/tickets', {})
        .then(body => body.json())
          .then(body => this.setState({Tickets : body , isLoading: false}))
          .catch(error => console.log(error)); 
      };

      componentDidMount(){
        this.fetchTickets()
    }


        // const ticketOddsResponse = await fetch('/api/ticketOdds');
        // const ticketOddsBody = await ticketOddsResponse.json();

        // for (const ticket of ticketsBody){
        //     let temporaryTicketOdds = [];
        //     for (const ticketOdd of ticketOddsBody){
        //         if( ticket.id === ticketOdd.ticket.id){
        //             temporaryTicketOdds = temporaryTicketOdds.concat(
        //                 {
        //                     id : ticketOdd.id,
        //                     odd : ticketOdd.odd,
        //                     type : ticketOdd.type,
        //                     home : ticketOdd.odds.match.home,
        //                     away : ticketOdd.odds.match.away,
        //                     matchTime : ticketOdd.odds.match.matchdate,
        //                 }
        //             );
        //         }
        //     }
        //     ticket.ticketOdds = temporaryTicketOdds;
        // }


    render() {
        const {Tickets, isLoading} = this.state;

        if(isLoading) 
            return (<div>Loading...</div>);

        return ( 
            <div>
            {
            Tickets.map( ticket =>
                <Table key={ticket.id}>
                    <thead style={{backgroundColor:"#dae0e5a1"}}>
                        <tr>
                            <th># {ticket.id}</th>
                            <th>Date</th>
                            <th></th>
                            <th>Possible Gain</th>
                            <th>Bet</th>
                            <th>Total Odd</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style={{backgroundColor:"#dae0e5a1"}}>
                            <td></td>
                            <td>{ticket.transaction.transactiondate}</td>
                            <td></td>
                            <td>{ticket.possiblegain}</td>
                            <td>{ticket.transaction.money} HRK</td>
                            <td>{ticket.totalodd}</td>
                        </tr>
                    {/* {ticket.ticketOdds.map(ticketOdd =>
                        <tr key={ticketOdd.id}>
                            <th scope="row" ></th>
                            <td>{ticketOdd.home}</td>
                            <td>{ticketOdd.away}</td>
                            <td>{ticketOdd.matchTime}</td>
                            <td>{ticketOdd.type}</td>
                            <td>{ticketOdd.odd}</td>
                        </tr>
                        )} */}
                    </tbody>
                </Table>
            )
            }
            </div>
        );
    }
}
 
export default Tickets;
