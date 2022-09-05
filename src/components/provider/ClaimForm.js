import react from 'react';
import "../../styles/ClaimForm.css";

class ClaimForm extends react.Component {

    render(){
        return (
            <div id="claimForm" className="claimPageChild">
                <h5>Claim Form</h5>
                <fieldset>
                    <legend>Principal Member</legend>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>First Name</div>
                            <input type='text' id='firstName' name='firstName' value={this.props.state.firstName} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Middle Name</div>
                            <input type='text' id='middleName' name='middleName' value={this.props.state.middleName} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Last Name</div>
                            <input type='text' id='lastName' name='lastName' value={this.props.state.lastName} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                    <div className="DetailDiv">
                        <div className="DetailField">
                            <label for='memberNumber'>Member Number </label>
                            <input type='number' id='memberNumber' name='memberNumber' value={this.props.state.memberNumber} onChange={this.props.changeHandler}></input>
                        </div>
                        <div className="DetailField">
                            <label for='dateOfBirth'>Date Of Birth </label>
                            <input type='date' id='dateOfBirth' name='dateOfBirth' value={this.props.state.dateOfBirth} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Patient</legend>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>First Name</div> 
                            <input type='text' id='patientFirstName' name='patientFirstName' value={this.props.state.patientFirstName} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Middle Name</div> 
                            <input type='text' id='patientMiddleName' name='patientMiddleName' value={this.props.state.patientMiddleName} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Last Name</div>
                            <input type='text' id='patientLastName' name='patientLastName' value={this.props.state.patientLastName} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                    <div className="DetailDiv">
                        <div className="DetailField">
                            <label for='beneficiaryId'>Beneficiary Id </label> 
                            <input type='number' id='beneficiaryId' name='beneficiaryId' value={this.props.state.beneficiaryId} onChange={this.props.changeHandler}></input>
                        </div>
                        <div className="DetailField">
                            <label for='patientDateOfBirth'>Date Of Birth </label>
                            <input type='date' id='patientDateOfBirth' name='patientDateOfBirth' value={this.props.state.patientDateOfBirth} onChange={this.props.changeHandler}></input>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Diagnosis</legend>
                    <input type='text' id='diagnosis' name='diagnosis' value={this.props.state.diagnosis} onChange={this.props.changeHandler}></input>
                </fieldset>
            </div>);
    }
}

export default ClaimForm;