import react from 'react';
import axios from 'axios';
import "../../styles/ClaimForm.css";

class ClaimForm extends react.Component {
    constructor(){
        super();
        this.state = {
            firstName:"",
            middleName:"",
            lastName: "",
            memberNumber: "",
            dateOfBirth: "",
            patientFirstName:"",
            patientMiddleName:"",
            patientLastName: "",
            beneficiaryId: '',
            patientDateOfBirth: "",
            diagnosis: ""
        };

        this.handleChange = this.handleChange.bind(this);
        this.submit = this.submit.bind(this);
    }

    handleChange(event){
        const {name, value} = event.target;
        this.setState({
            [name] : value
        });
    }

    async submit(){
        
        await axios.post('http://localhost:8080/provider/claim', this.state)
            .then(response => console.log({data:response.data}))
            .catch(error => console.log({data:error.message}));
    }

    render(){
        return (
            <div id="claimForm" className="claimPageChild">
                <h5>Claim Form</h5>
                <fieldset>
                    <legend>Principal Member</legend>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>First Name</div>
                            <input type='text' id='firstName' name='firstName' value={this.state.firstName} onChange={this.handleChange}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Middle Name</div>
                            <input type='text' id='middleName' name='middleName' value={this.state.middleName} onChange={this.handleChange}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Last Name</div>
                            <input type='text' id='lastName' name='lastName' value={this.state.lastName} onChange={this.handleChange}></input>
                        </div>
                    </div>
                    <div className="DetailDiv">
                        <div className="DetailField">
                            <label for='memberNumber'>Member Number </label>
                            <input type='number' id='memberNumber' name='memberNumber' value={this.state.memberNumber} onChange={this.handleChange}></input>
                        </div>
                        <div className="DetailField">
                            <label for='dateOfBirth'>Date Of Birth </label>
                            <input type='date' id='dateOfBirth' name='dateOfBirth' value={this.state.dateOfBirth} onChange={this.handleChange}></input>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Patient</legend>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>First Name</div> 
                            <input type='text' id='patientFirstName' name='patientFirstName' value={this.state.patientFirstName} onChange={this.handleChange}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Middle Name</div> 
                            <input type='text' id='patientMiddleName' name='patientMiddleName' value={this.state.patientMiddleName} onChange={this.handleChange}></input>
                        </div>
                    </div>
                    <div className="NameDiv">
                        <div className="NameField">
                            <div>Last Name</div>
                            <input type='text' id='patientLastName' name='patientLastName' value={this.state.patientLastName} onChange={this.handleChange}></input>
                        </div>
                    </div>
                    <div className="DetailDiv">
                        <div className="DetailField">
                            <label for='beneficiaryId'>Beneficiary Id </label> 
                            <input type='number' id='beneficiaryId' name='beneficiaryId' value={this.state.beneficiaryId} onChange={this.handleChange}></input>
                        </div>
                        <div className="DetailField">
                            <label for='patientDateOfBirth'>Date Of Birth </label>
                            <input type='date' id='patientDateOfBirth' name='patientDateOfBirth' value={this.state.patientDateOfBirth} onChange={this.handleChange}></input>
                        </div>
                    </div>
                </fieldset>
                <fieldset>
                    <legend>Diagnosis</legend>
                    <input type='text' id='diagnosis' name='diagnosis' value={this.state.diagnosis} onChange={this.handleChange}></input>
                </fieldset>
                <button onClick={this.submit}>Submit</button>
            </div>);
    }
}

export default ClaimForm;