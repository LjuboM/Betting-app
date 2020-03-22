import React, { Component } from 'react';


class Header extends Component {
    state = {
    }

    render() { 
        return (
        <div id="header">
            <p><b>Betting App</b></p>
            <p>{this.props.userName} | {this.props.moneyInWallet} HRK</p>
        </div>
        )
    }
}
 
export default Header;
