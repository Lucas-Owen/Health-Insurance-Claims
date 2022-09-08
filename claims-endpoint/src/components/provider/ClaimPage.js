import react from 'react';
import axios from 'axios';

import {Buffer} from 'buffer';

import InvoiceForm from './InvoiceForm';
import ClaimForm from './ClaimForm';

import '../../styles/ClaimPage.css';

class ClaimPage extends react.Component {
    constructor(props){
        super();

        this.state = {
            username: 'admin',
            password: 'password',

            // Details on claim form
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
            diagnosis: '',

            // Details on invoice
            providerName: "",
            invoiceId: "",
            invoiceGrossAmount: 0,
            invoiceLines:[],
            newLine: {
                category: '',
                description: '',
                quantity: 1,
                pricePerUnit: 0,
                cost: 0,
                id : ''
            },
            lineCount : 0
        };

        this.submitClaim = this.submitClaim.bind(this);
        this.handleClaimFormChange = this.handleClaimFormChange.bind(this);
        this.handleInvoiceFormChange = this.handleInvoiceFormChange.bind(this);
        this.handleNewLineChange = this.handleNewLineChange.bind(this);
        this.addInvoiceLine = this.addInvoiceLine.bind(this);
        this.removeInvoiceLine = this.removeInvoiceLine.bind(this);
        this.selectInvoiceLine = this.selectInvoiceLine.bind(this);
        this.submitInvoice = this.submitInvoice.bind(this);
        this.calcTotal = this.calcTotal.bind(this);
    }

    async handleClaimFormChange(event){
        const {name, value} = event.target;

        await this.setState({
            [name] : value
        });
    }

    async handleInvoiceFormChange(event){
        const {name, value} = event.target;

        await this.setState({
            [name] : value
        }); 
    }
    
    async handleNewLineChange(event){
        const {name, value} = event.target;
        const line = this.state.newLine;
        line[name] = value;
        line.cost = line.quantity * line.pricePerUnit;
        this.calcTotal();
        
        await this.setState({
            newLine : line
        });
        
    }

    async addInvoiceLine(){

        await this.setState( oldState => {
            
            let newState = oldState;
            let line = {...oldState.newLine};

            line.id = oldState.lineCount;
            newState.invoiceLines.push(line);
            newState.lineCount = oldState.lineCount + 1;
            
            newState.newLine = {
                category: '',
                description: '',
                quantity: 1,
                pricePerUnit: 0,
                cost: 0,
                id : ''
            };

            return newState;
        });
        
        this.calcTotal();
    }

    async removeInvoiceLine(id){
        await this.setState(oldState => {
            let invoiceLines = oldState.invoiceLines.filter((line) => line.id !== id);
            oldState.invoiceLines = invoiceLines;
            return oldState;
        });

        this.calcTotal();
    }

    async selectInvoiceLine(id){
        await this.setState(oldState => {
            let newState = oldState;
            let newLine = newState.invoiceLines.find((line) => line.id === id);
            newState.newLine = {...newLine};
            return newState;
        });
    }

    async calcTotal(){
        const total = this.props.state.invoiceLines.reduce((prev, curr) =>{
            return curr.cost + prev;
        }, 0);

        await this.setState({
            invoiceGrossAmount:total
        });
    }

    async submitClaim(){

        const claimDetails = {
            firstName: this.state.firstName,
            middleName: this.state.middleName,
            lastName: this.state.lastName,
            memberNumber: this.state.memberNumber,
            dateOfBirth: this.state.dateOfBirth,
            patientFirstName: this.state.patientFirstName,
            patientMiddleName: this.state.patientMiddleName,
            patientLastName: this.state.patientLastName,
            beneficiaryId: this.state.beneficiaryId,
            patientDateOfBirth: this.state.patientDateOfBirth,
            diagnosis: this.state.diagnosis
        };

        console.log(claimDetails);
        
        const token = Buffer.from(`${this.state.username}:${this.state.password}`, 'utf8').toString('base64');
        const header = {
            headers: {
              'Authorization': `Basic ${token}`
            }
        }
        
        await axios.post('http://localhost:8080/provider/claim', claimDetails, header)
            .then(response => console.log({data:response.data}))
            .catch(error => console.log({data:error.message}));
    }

    
    async submitInvoice(){
        const username = 'admin';
        const password = 'password';
        const token = Buffer.from(`${username}:${password}`, 'utf8').toString('base64');
        const header = {
            headers: {
              'Authorization': `Basic ${token}`
            }
        }

        await axios.post('http://localhost:8080/provider/invoice', this.props.state, header)
            .then(response => console.log({data:response.data}))
            .catch(error => console.log({data:error.message}));
    }

    render(){
        return (
            <div id='claimPage'>
                <div id='claimPageForms'>
                    <ClaimForm 
                        state={this.state} 
                        changeHandler={this.handleClaimFormChange}/>
                    <InvoiceForm 
                        state={this.state} 
                        changeHandler={this.handleInvoiceFormChange} 
                        handleNewLineChange={this.handleNewLineChange}
                        addInvoiceLine={this.addInvoiceLine}
                        removeInvoiceLine={this.removeInvoiceLine}
                        calcTotal={this.calcTotal}
                        selectInvoiceLine={this.selectInvoiceLine}/>
                </div>


                <div id='claimPageSubmitButtons'>
                    <button onClick={this.submitClaim}>Submit Claim</button>
                    <button onClick={this.submitInvoice}>Submit Invoice</button>
                </div>
            </div>
        );
    }
}

export default ClaimPage;