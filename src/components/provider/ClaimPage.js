import react from 'react';
import axios from 'axios';

import InvoiceForm from './InvoiceForm';
import ClaimForm from './ClaimForm';

import '../../styles/ClaimPage.css';

class ClaimPage extends react.Component {
    constructor(props){
        super();

        this.state = {
            firstName:'',
            middleName:'',
            lastName: '',
            memberNumber: '',
            dateOfBirth: '',
            patientFirstName:'',
            patientMiddleName:'',
            patientLastName: '',
            beneficiaryId: '',
            patientDateOfBirth: '',
            diagnosis: ''
        };

        this.submitClaim = this.submitClaim.bind(this);
        this.handleClaimFormChange = this.handleClaimFormChange.bind(this);
    }

    async handleClaimFormChange(event){
        const {name, value} = event.target;

        await this.setState({
            [name] : value
        });
    }

    async submitClaim(){
        
        await axios.post('http://localhost:8080/provider/claim', this.state)
            .then(response => console.log({data:response.data}))
            .catch(error => console.log({data:error.message}));
    }

    render(){
        return (
            <div id='claimPage'>
                <ClaimForm state={this.state} changeHandler={this.handleClaimFormChange}/>
                <InvoiceForm/>
                <button onClick={this.submitClaim}>Submit Claim</button>
            </div>
        );
    }
}

export default ClaimPage;