import React, { Component } from 'react';
import OddsOffer from './OddsOffer';
import NewTicket from './NewTicket';

class PlayTicket extends Component {
    state = {  }
    render() { 
        return (
                <div id="playTicketContainer">
                    <OddsOffer id="oddsOffer"/>
                    <NewTicket/> 
                </div>
        );
    }
}
 
export default PlayTicket;