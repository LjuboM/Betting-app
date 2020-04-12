import React, { Component } from 'react';
import { Table, Button, Alert, InputGroup, InputGroupAddon, Input } from 'reactstrap';
import { ExportableContext } from './providers/MyProvider';
import PopUpPlayTicket from './PopUpPlayTicket';

class NewTicket extends Component {
    state = { }

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
                            <th><Button color="danger" onClick={ () => value.deleteNewTicket()}> Delete all </Button></th>
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
                            {value.state.alertMessage}
                    </Alert>}
                    <div>
                        <InputGroup style={{margin:"15px", width:"96%"}}>
                            <InputGroupAddon addonType="prepend">HRK</InputGroupAddon>
                                <Input placeholder="Amount" min={1} type="number" step="1" onChange={(event) => {value.handleBetMoneyInput(event);}}/>
                            <InputGroupAddon addonType="append"> <Button color="primary" onClick={() => { value.togglePopUp(); }}> Play Ticket </Button> </InputGroupAddon>
                        </InputGroup>
                    </div>
                    {value.state.popUpSeen ? <PopUpPlayTicket/> : null}
                </React.Fragment>
                )}
            </ExportableContext>
            </div>
         );
    }
}

 
export default NewTicket;