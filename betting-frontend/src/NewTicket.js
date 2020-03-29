import React, { Component } from 'react';
import { Table } from 'reactstrap';
import { Button, Alert } from 'reactstrap';
import { ExportableContext } from './providers/MyProvider';

class NewTicket extends Component {
    constructor(props){
        super(props)
  
        this.state = { 
          TicketOdds : this.emptyTicketOdds
         }
         this.handlePlayingTicket= this.handlePlayingTicket.bind(this);
    }
    emptyTicketOdds = [
        {
        "ticket": {
            "totalodd": 1,
            "possiblegain": 1,
            "transaction": {
                "money": 1,
                    "user": {
                    "id": 1
                }
            }
        },
        "odds": {
            "id": 1,
            "type": "Basic",
            "match": {
            	"id": 1,
            	"matchdate": "2022-02-22T22:00:00.000Z"
            }
        },
        "odd": 1.0,
        "type": "1"
        }
    ]

    async handlePlayingTicket(){
        const TicketOdds = this.state.TicketOdds;
        await fetch(`/api/ticket`, {
            method : 'POST',
            headers : {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body : JSON.stringify(TicketOdds),
        });
        const newLocal = "/transactions";
    }

    render() { 
        // const {TicketOdds} = this.state;
        return (
            <div id="newTicket">
            <ExportableContext>
                {(value) => (
                <Table striped >
                    <thead>
                    <tr>
                        <th>Select all</th>
                        <th>Delete all</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row" >...</th>
                            <td>...</td>
                        </tr>
                    </tbody>
                    <Button color="primary" onClick={() => { this.handlePlayingTicket(); value.increaseMoneyValue(-1); }}>Place a Bet</Button>
                </Table>
                )}
            </ExportableContext>
            </div>
         );
    }
}

 
export default NewTicket;