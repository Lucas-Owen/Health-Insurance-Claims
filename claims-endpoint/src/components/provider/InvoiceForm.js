import react from 'react';
import InvoiceLine from './InvoiceLine';

import "../../styles/InvoiceForm.css";

class InvoiceForm extends react.Component {

    render(){

        return (
            <form id="invoiceForm" className="claimPageChild" onSubmit={this.props.submitInvoice}>
                <h3>Invoice</h3>
                <div>Provider: <input type='text' value={this.props.state.providerName} name='providerName' onChange={this.props.changeHandler} required/></div>
                <div>Invoice Reference <input type='text' value={this.props.state.invoiceReference} name='invoiceReference' onChange={this.props.changeHandler} required/></div>
                <fieldset>
                    <legend>Patient</legend>
                    <div>
                        Name: {this.props.state.patientFirstName + " " + this.props.state.patientMiddleName + " " + this.props.state.patientLastName}
                    </div>
                    <div>
                        Member Number: {this.props.state.beneficiaryId}
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Invoice Lines</legend>
                    <div className="LineDiv">
                        <label htmlFor="Category">Category </label>
                        <input type='text' onChange={this.props.handleNewLineChange} name='category' value={this.props.state.newLine.category}/>
                    </div>
                    <div className="LineDiv">
                        <label htmlFor='Description'>Description </label>
                        <input type='text' onChange={this.props.handleNewLineChange} name='description' value={this.props.state.newLine.description}/>
                    </div>
                    <div className="LineDiv">
                        <label htmlFor='Price'>Price </label>
                        <input type='number' onChange={this.props.handleNewLineChange} name='pricePerUnit' value={this.props.state.newLine.pricePerUnit}/></div>
                    <div className="LineDiv">
                        <label htmlFor='Quantity'>Quantity</label>
                        <input type='number' onChange={this.props.handleNewLineChange} name='quantity' value={this.props.state.newLine.quantity}/>
                    </div>
                    <button onClick={this.props.addInvoiceLine}>Add Line</button>
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
                            {this.props.state.invoiceLines.map(line => (<InvoiceLine lines={line} key={line.id} onClickTr={() => this.props.selectInvoiceLine(line.id)} removeBtnClick={() => this.props.removeInvoiceLine(line.id)}/>))}
                            <tr>
                                <td colSpan='4'>Total</td>
                                <td>{this.props.state.invoiceGrossAmount}</td>
                            </tr>
                        </tbody>
                    </table>
                </fieldset>
                <div className='centerContainer'>
                    <input className='claimPageSubmitButton' type='submit' value='File Invoice'/>
                </div>
            </form>);
    }
}

export default InvoiceForm;