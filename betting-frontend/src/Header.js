import React, { Component } from 'react';


class Header extends Component {
    state = {
        User : []
    }

    async componentDidMount(){
        const response = await fetch('/api/user/1');
        const body = await response.json();
        this.setState({User : body});
    }
    render() { 
        const {User} = this.state;
        return (
        <div id="header">
            <p><b>Betting App</b></p>
            <p>{User.name} | {User.money} HRK</p>
        </div>
        )
    }
}
 
export default Header;
