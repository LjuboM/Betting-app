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
            <ExportableContext>
                {(value) => (
                    Types.map( TypesPerSport =>
                        <Table striped key={TypesPerSport.id}>
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
                                <tr>
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
                                <tr>
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
                        {/* {value.state.Odds.map( odd =>
                            <tr>
                                <td>{odd.match.home}</td>
                                <td>{odd.match.away}</td>
                                <td>{odd.match.matchdate}</td>
                                <td>{odd.odd1}</td>
                                <td>{odd.odd2}</td>
                                <td>{odd.odd3}</td>
                                <td>{odd.odd4}</td>
                                <td>{odd.odd5}</td>
                                <td>{odd.odd6}</td>
                            </tr>
                        ) */}
                        </tbody>
                    </Table>
                    )


                // value.state.Odds.map( odd =>
                //     <Table striped key={odd.id}>
                //         <thead>
                //             <tr>
                //                 <th style={{color:"blue"}}>{odd.match.types.name}</th>
                //             </tr>
                //             <tr>
                //                 <th></th>
                //                 <th></th>
                //                 <th></th>
                //                 <th>{odd.match.types.type1}</th>
                //                 <th>{odd.match.types.type2}</th>
                //                 <th>{odd.match.types.type3}</th>
                //                 <th>{odd.match.types.type4}</th>
                //                 <th>{odd.match.types.type5}</th>
                //                 <th>{odd.match.types.type6}</th>
                //             </tr>
                //         </thead>
                //         <tbody>
                //             <tr>
                //                 <td>{odd.match.home}</td>
                //                 <td>{odd.match.away}</td>
                //                 <td>{odd.match.matchdate}</td>
                //                 <td>{odd.odd1}</td>
                //                 <td>{odd.odd2}</td>
                //                 <td>{odd.odd3}</td>
                //                 <td>{odd.odd4}</td>
                //                 <td>{odd.odd5}</td>
                //                 <td>{odd.odd6}</td>
                //             </tr>
                //         </tbody>
                //     </Table>
                // )

                )}
            </ExportableContext>



        );
    }
}
 
export default OddsOffer;