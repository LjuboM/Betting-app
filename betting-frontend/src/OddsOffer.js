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
                            <thead id={TypesPerSport.name}>
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
                                    <tr style={{color:"blue"}} key={specialOfferOdd.id} className={value.isPairSelected(specialOfferOdd.id) === true ? 'selectedPair' : ''}>
                                        <td>{specialOfferOdd.match.home}</td>
                                        <td>{specialOfferOdd.match.away}</td>
                                        <td>{specialOfferOdd.match.matchdate}</td>
                                        <td className={value.isOddTypeOfPairSelected(specialOfferOdd.id, TypesPerSport.type1) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(specialOfferOdd.odd1, TypesPerSport.type1, specialOfferOdd)}>
                                            {specialOfferOdd.odd1}
                                         </td>
                                        <td className={value.isOddTypeOfPairSelected(specialOfferOdd.id, TypesPerSport.type2) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(specialOfferOdd.odd2, TypesPerSport.type2, specialOfferOdd)}>
                                            {specialOfferOdd.odd2}
                                         </td>
                                        <td className={value.isOddTypeOfPairSelected(specialOfferOdd.id, TypesPerSport.type3) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(specialOfferOdd.odd3, TypesPerSport.type3, specialOfferOdd)}>
                                            {specialOfferOdd.odd3}
                                         </td>
                                        <td className={value.isOddTypeOfPairSelected(specialOfferOdd.id, TypesPerSport.type4) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(specialOfferOdd.odd4, TypesPerSport.type4, specialOfferOdd)}>
                                            {specialOfferOdd.odd4}
                                         </td>
                                        <td className={value.isOddTypeOfPairSelected(specialOfferOdd.id, TypesPerSport.type5) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(specialOfferOdd.odd5, TypesPerSport.type5, specialOfferOdd)}>
                                            {specialOfferOdd.odd5}
                                         </td>
                                        <td className={value.isOddTypeOfPairSelected(specialOfferOdd.id, TypesPerSport.type6) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(specialOfferOdd.odd6, TypesPerSport.type6, specialOfferOdd)}>
                                            {specialOfferOdd.odd6}
                                         </td>
                                    </tr>
                                )
                                }
                                {
                                Odds.filter( basicOfferOdds => basicOfferOdds.match.types.name === TypesPerSport.name && basicOfferOdds.type === "Basic")
                                .map( basicOfferOdd =>                             
                                    <tr key={basicOfferOdd.id} className={value.isPairSelected(basicOfferOdd.id) === true ? 'selectedPair' : ''}>
                                        <td>{basicOfferOdd.match.home}</td>
                                        <td>{basicOfferOdd.match.away}</td>
                                        <td>{basicOfferOdd.match.matchdate}</td>
                                        <td className={value.isOddTypeOfPairSelected(basicOfferOdd.id, TypesPerSport.type1) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(basicOfferOdd.odd1, TypesPerSport.type1, basicOfferOdd)}>
                                                {basicOfferOdd.odd1}
                                        </td>
                                        <td className={value.isOddTypeOfPairSelected(basicOfferOdd.id, TypesPerSport.type2) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'}
                                            onClick={ () => value.modifyPair(basicOfferOdd.odd2, TypesPerSport.type2, basicOfferOdd)}>
                                            {basicOfferOdd.odd2}
                                        </td>
                                        <td className={value.isOddTypeOfPairSelected(basicOfferOdd.id, TypesPerSport.type3) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'} 
                                            onClick={ () => value.modifyPair(basicOfferOdd.odd3, TypesPerSport.type3, basicOfferOdd)}>
                                            {basicOfferOdd.odd3}
                                        </td>
                                        <td className={value.isOddTypeOfPairSelected(basicOfferOdd.id, TypesPerSport.type4) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'} 
                                            onClick={ () => value.modifyPair(basicOfferOdd.odd4, TypesPerSport.type4, basicOfferOdd)}>
                                            {basicOfferOdd.odd4}
                                        </td>
                                        <td className={value.isOddTypeOfPairSelected(basicOfferOdd.id, TypesPerSport.type5) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'} 
                                            onClick={ () => value.modifyPair(basicOfferOdd.odd5, TypesPerSport.type5, basicOfferOdd)}>
                                            {basicOfferOdd.odd5}
                                        </td>
                                        <td className={value.isOddTypeOfPairSelected(basicOfferOdd.id, TypesPerSport.type6) === true ? 'selectedTypeOfPair oddsOfferFakeButton' : 'oddsOfferFakeButton'} 
                                            onClick={ () => value.modifyPair(basicOfferOdd.odd6, TypesPerSport.type6, basicOfferOdd)}>
                                            {basicOfferOdd.odd6}
                                        </td>
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