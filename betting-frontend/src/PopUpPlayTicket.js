import React, { Component } from 'react';
import { ExportableContext } from './providers/MyProvider';
import { Table, Button } from 'reactstrap';

class PopUpPlayTicket extends Component {
    state = {  }
    render() { 
        return (   
        <ExportableContext > 
            {(value) => (
            <div className="popup">
                <div className="popup-content">
                    <Table striped >
                        <thead>
                        <tr>
                            <th>Money: <b>{value.state.money}</b></th>
                            <th>Possible Gain: <b>{value.state.possibleGain.toFixed(2)}</b> HRK</th>
                            <th></th>
                            <th style={{color:"blue"}}>Total Odd: <b>{value.state.totalOdd.toFixed(2)}</b></th>
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
                    </Table>
                    <div style={{display:"flex", justifyContent:"space-between"}}>
                        <Button color="danger" onClick={() => { value.removePopUp(); }}> Cancel </Button>
                        <Button color="primary" onClick={() => { value.playTicket(); value.changeMoneyValue(value.state.money, false); }}> Confirm </Button>
                    </div>
                </div>
            </div>
            )}
        </ExportableContext>
       );
    }
}
 
export default PopUpPlayTicket;