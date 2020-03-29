import React, { Component } from 'react';
import { Table } from 'reactstrap';
import { ExportableContext } from './providers/MyProvider';



class OddsOffer extends Component {
    constructor(props){
        super(props)

        this.state = { 
            isLoading : true,
            Odds : [],
            Types : []
         }
    }
    
    fetchTypes = () => {
        fetch('/api/types', {})
        .then(body => body.json())
          .then(body => this.setState({Types : body}))
          .catch(error => console.log(error)); 
      };

    fetchOdds = () => {
        fetch('/api/odds', {})
        .then(body => body.json())
        .then(body => this.setState({Odds : body , isLoading: false}))
        .catch(error => console.log(error)); 
      };

    componentDidMount(){
        this.fetchTypes();
        this.fetchOdds()
    }

    render() {
         const {Odds, Types, isLoading} = this.state;

         if(isLoading) 
             return (<div>Loading...</div>);

        return (
            <div >
            <ExportableContext >

                   {(value) => (
                        Types.map( TypesPerSport =>
                            <Table striped responsive key={TypesPerSport.id}>
                            <thead>
                                <tr>
                                    <th>{TypesPerSport.name}</th>
                                    <th></th>
                                    <th></th>
                                    <th>{TypesPerSport.type1}</th>
                                    <th>{TypesPerSport.type2}</th>
                                    <th>{TypesPerSport.type3}</th>
                                    <th>{TypesPerSport.type4}</th>
                                    <th>{TypesPerSport.type5}</th>
                                    <th>{TypesPerSport.type6}</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                Odds.filter( specialOfferOdds => specialOfferOdds.match.types.name === TypesPerSport.name && specialOfferOdds.type === "Special offer")
                                .map( specialOfferOdd =>                             
                                    <tr key={specialOfferOdd.id}>
                                        <td style={{color:"blue"}}>{specialOfferOdd.match.home}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.match.away}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.match.matchdate}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.odd1}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.odd2}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.odd3}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.odd4}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.odd5}</td>
                                        <td style={{color:"blue"}}>{specialOfferOdd.odd6}</td>
                                    </tr>
                                )
                                }
                                {
                                Odds.filter( specialOfferOdds => specialOfferOdds.match.types.name === TypesPerSport.name && specialOfferOdds.type === "Basic")
                                .map( specialOfferOdd =>                             
                                    <tr key={specialOfferOdd.id}>
                                        <td>{specialOfferOdd.match.home}</td>
                                        <td>{specialOfferOdd.match.away}</td>
                                        <td>{specialOfferOdd.match.matchdate}</td>
                                        <td>{specialOfferOdd.odd1}</td>
                                        <td>{specialOfferOdd.odd2}</td>
                                        <td>{specialOfferOdd.odd3}</td>
                                        <td>{specialOfferOdd.odd4}</td>
                                        <td>{specialOfferOdd.odd5}</td>
                                        <td>{specialOfferOdd.odd6}</td>
                                    </tr>
                                )
                                }
                            </tbody>
                        </Table>
                        )
                    )}

            </ExportableContext>
            </div>
        );
    }
}
 
export default OddsOffer;