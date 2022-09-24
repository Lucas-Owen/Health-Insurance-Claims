import react from 'react';
import axios from 'axios';

class API extends react.Component{
    constructor(){
        super();
        this.state = {data:'Nothing'}
    }

    async componentDidMount(){
        const invoice = {
            providerName: "Aga Khan University Hospital",
            invoiceId: "ORE232535",
            beneficiaryFirstName: "Lucas",
            beneficiaryMiddleName: "Owen",
            beneficiaryLastName: "Sangura",
            beneficiaryId: 12223834,
            invoiceGrossAmount: 4000,
            invoiceLines:{"Consultation":2250, "Prescription":1750}
        };
        await axios.post('http://localhost:8080/provider/invoice', invoice)
            .then(response => this.setState({data:response.data}))
            .catch(error => this.setState({data:error.message}));
    }

    render(){
        return (<div>{this.state.data}</div>);
    }
}

export default API;