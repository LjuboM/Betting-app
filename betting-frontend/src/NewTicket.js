import React, { Component } from 'react';
import { Table } from 'reactstrap';
import { Button, Alert } from 'reactstrap';
import { InputGroup, InputGroupAddon, Input } from 'reactstrap';
import { ExportableContext } from './providers/MyProvider';

class NewTicket extends Component {
    constructor(props){
        super(props)
  
        this.state = { 
          TicketOdds : this.emptyTicketOdds,
          isHidden : true
         }
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

    // this.setState({
    //     isHidden: false
    // })

    render() { 
        // const {TicketOdds} = this.state;
        return (
            <div id="newTicket">
            <ExportableContext>
                {(value) => (
                <React.Fragment>
                    <Table striped >
                        <thead>
                        <tr>
                            <th>Select all</th>
                            <th>...</th>
                            <th>...</th>
                            <th>Delete all</th>
                        </tr>
                        </thead>
                        {value.state.NewTicket.map( pair =>
                        <tbody key={pair.odds.id}>
                            <tr>
                                <td>{pair.odds.match.home}</td>
                                <td>{pair.odds.match.away}</td>
                                <td>{pair.type}</td>
                                <td>{pair.odd}</td>
                            </tr>
                        </tbody>
                        )}
                        <tbody>
                            <tr style={{backgroundColor:"white"}}>
                                <td>Money: {value.state.money}</td>
                                <td>Possible Gain: {value.state.possibleGain.toFixed(2)} HRK</td>
                                <td></td>
                                <td style={{color:"blue"}}>{value.state.totalOdd.toFixed(2)}</td>
                            </tr>
                        </tbody>
                    </Table>
                    <div>
                        <InputGroup style={{margin:"15px", width:"96%"}}>
                            <InputGroupAddon addonType="prepend">HRK</InputGroupAddon>
                                <Input placeholder="Amount" min={1} type="number" step="1" onChange={(event) => {value.handleBetMoneyInput(event);}}/>
                            <InputGroupAddon addonType="append"> <Button color="primary" onClick={() => { value.playTicket(); value.changeMoneyValue(-value.state.money); }}> Place a Bet </Button> </InputGroupAddon>
                        </InputGroup>
                        {!this.state.isHidden && 
                        <Alert color="danger">
                        Only positive integer values higher than 1 are accepted!
                        </Alert>}
                    </div>
                </React.Fragment>
                )}
            </ExportableContext>
            </div>
         );
    }
}

 
export default NewTicket;