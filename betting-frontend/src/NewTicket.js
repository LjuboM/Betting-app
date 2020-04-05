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

    render() { 
        return (
            <div id="newTicket">
            <ExportableContext>
                {(value) => (
                <React.Fragment>
                    <Table striped >
                        <thead>
                        <tr>
                            <th>New Ticket</th>
                            <th></th>
                            <th></th>
                            <th></th>
                            <th className={"delete"} onClick={ () => value.deleteNewTicket()}>Delete all</th>
                        </tr>
                        </thead>
                        {value.state.NewTicket.map( pair =>
                        <tbody key={pair.odds.id}>
                            <tr>
                                <td className={"delete"} onClick={ () => value.deletePair(pair.odds.id, pair.odd)} ><b>X</b></td>
                                <td>{pair.odds.match.home}</td>
                                <td>{pair.odds.match.away}</td>
                                <td>{pair.type}</td>
                                <td>{pair.odd}</td>
                            </tr>
                        </tbody>
                        )}
                        <tbody>
                            <tr style={{backgroundColor:"white"}}>
                                <td>Money: <b>{value.state.money}</b></td>
                                <td>Possible Gain: <b>{value.state.possibleGain.toFixed(2)}</b> HRK</td>
                                <td></td>
                                <td></td>
                                <td style={{color:"blue"}}><b>{value.state.totalOdd.toFixed(2)}</b></td>
                            </tr>
                        </tbody>
                    </Table>
                    {!value.state.isHidden && 
                        <Alert color="danger">
                            {value.state.allertMessage}
                    </Alert>}
                    <div>
                        <InputGroup style={{margin:"15px", width:"96%"}}>
                            <InputGroupAddon addonType="prepend">HRK</InputGroupAddon>
                                <Input placeholder="Amount" min={1} type="number" step="1" onChange={(event) => {value.handleBetMoneyInput(event);}}/>
                            <InputGroupAddon addonType="append"> <Button color="primary" onClick={() => { value.playTicket(); value.changeMoneyValue(value.state.money, false); }}> Place a Bet </Button> </InputGroupAddon>
                        </InputGroup>
                    </div>
                </React.Fragment>
                )}
            </ExportableContext>
            </div>
         );
    }
}

 
export default NewTicket;