import React, { Component } from 'react';
import { Table } from 'reactstrap';

class PlayTicket extends Component {
    constructor(props){
        super(props)

        this.state = { 
            isLoading : true,
            Odds : []
         }
    }
    
    fetchOdds = () => {
        fetch('/api/odds', {})
        .then(body => body.json())
          .then(body => this.setState({Odds : body , isLoading: false}))
          .catch(error => console.log(error)); 
      };

      componentDidMount(){
        this.fetchOdds()
    }

    render() {
        const {Odds, isLoading} = this.state;

        if(isLoading) 
            return (<div>Loading...</div>);

        return ( 
            <div>
            {
            Odds.map( odd =>
                <Table striped key={odd.id}>
                    <thead>
                        <tr>
                            <p style={{color:"blue"}}>{odd.match.types.name}</p>
                        </tr>
                        <tr>
                            <th>Home</th>
                            <th>Away</th>
                            <th>Match Date</th>
                            <th>{odd.match.types.type1}</th>
                            <th>{odd.match.types.type2}</th>
                            <th>{odd.match.types.type3}</th>
                            <th>{odd.match.types.type4}</th>
                            <th>{odd.match.types.type5}</th>
                            <th>{odd.match.types.type6}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>{odd.match.home}</th>
                            <th>{odd.match.away}</th>
                            <th>{odd.match.matchdate}</th>
                            <th>{odd.odd1}</th>
                            <th>{odd.odd2}</th>
                            <th>{odd.odd3}</th>
                            <th>{odd.odd4}</th>
                            <th>{odd.odd5}</th>
                            <th>{odd.odd6}</th>
                        </tr>
                    </tbody>
                </Table>
            )
            }
            </div>
        );
    }
}
 
export default PlayTicket;