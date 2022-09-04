import react from 'react';
import InvoiceLine from './InvoiceLine';
import axios from 'axios';

import "../../styles/InvoiceForm.css";

class InvoiceForm extends react.Component {
    constructor(){
        super();
        this.state = {
            username: "",
            password: "",
            providerName: "",
            invoiceId: "",
            beneficiaryFirstName: "",
            beneficiaryMiddleName: "",
            beneficiaryLastName: "",
            beneficiaryId: 0,
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

        this.handleChange = this.handleChange.bind(this);
        this.handleNewLineChange = this.handleNewLineChange.bind(this);
        this.addLine = this.addLine.bind(this);
        this.calcTotal = this.calcTotal.bind(this);
        this.submit = this.submit.bind(this);
        this.selectLine = this.selectLine.bind(this);
        this.removeLine = this.removeLine.bind(this);
    }
    async addLine(){

        await this.setState( oldState => {
            console.log("Here");
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
        
        await this.calcTotal();
    }

    
    async removeLine(id){
        await this.setState(oldState => {
            let invoiceLines = oldState.invoiceLines.filter((line) => line.id !== id);
            oldState.invoiceLines = invoiceLines;
            return oldState;
        });

        this.calcTotal();
    }

    async selectLine(id){
        await this.setState(oldState => {
            let newState = oldState;
            let newLine = newState.invoiceLines.find((line) => line.id === id);
            newState.newLine = {...newLine};
            return newState;
        });
    }

    async handleChange(event){
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

    async calcTotal(){
        const total = this.state.invoiceLines.reduce((prev, curr) =>{
            return curr.cost + prev;
        }, 0);

        await this.setState({
            invoiceGrossAmount:total
        });
    }

    async submit(){
        const username = 'admin';
        const password = 'password';
        const token = Buffer.from(`${username}:${password}`, 'utf8').toString('base64');
        const header = {
            headers: {
              'Authorization': `Basic ${token}`
            }
        }

        console.log(header.headers);

        await axios.post('http://localhost:8080/provider/invoice', this.state, header)
            .then(response => console.log({data:response.data}))
            .catch(error => console.log({data:error.message}));
    }

    render(){

        return (
            <div id="invoiceForm" className="claimPageChild">
                <h5>Invoice</h5>
                <div>Provider: {this.state.providerName}</div>
                <div>Invoice Reference <input type='text' value={this.state.invoiceId} name='invoiceId' onChange={this.handleChange}/></div>
                <fieldset>
                    <legend>Patient</legend>
                    <div>
                        Name: {this.state.beneficiaryFirstName + " " + this.state.beneficiaryMiddleName + " " + this.state.beneficiaryLastName}
                    </div>
                    <div>
                        Member Number: {this.state.beneficiaryId}
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Invoice Lines</legend>
                    <div className="LineDiv">
                        <label for="Category">Category </label>
                        <input type='text' onChange={(this.handleNewLineChange)} name='category' value={this.state.newLine.category}/>
                    </div>
                    <div className="LineDiv">
                        <label for='Description'>Description </label>
                        <input type='text' onChange={(this.handleNewLineChange)} name='description' value={this.state.newLine.description}/>
                    </div>
                    <div className="LineDiv">
                        <label for='Price'>Price </label>
                        <input type='number' onChange={(this.handleNewLineChange)} name='pricePerUnit' value={this.state.newLine.pricePerUnit}/></div>
                    <div className="LineDiv">
                        <label for='Quantity'>Quantity</label>
                        <input type='number' onChange={(this.handleNewLineChange)} name='quantity' value={this.state.newLine.quantity}/>
                    </div>
                    <button onClick={this.addLine}>Add Line</button>
                    <table>
                        <thead>
                            <tr>
                                <th>Category</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th>Cost</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.invoiceLines.map(line => (<InvoiceLine lines={line} key={line.id} onClickTr={() => this.selectLine(line.id)} removeBtnClick={() => this.removeLine(line.id)}/>))}
                            <tr>
                                <td colSpan='4'>Total</td>
                                <td>{this.state.invoiceGrossAmount}</td>
                            </tr>
                        </tbody>
                    </table>
                </fieldset>
                <button onClick={this.submit}>Submit</button>
            </div>);
    }
}

export default InvoiceForm;