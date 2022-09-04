import react from 'react';

import InvoiceForm from './InvoiceForm';
import ClaimForm from './ClaimForm';

import '../../styles/ClaimPage.css';

class ClaimPage extends react.Component {
    constructor(){
        super();

        this.state = {}
    }

    render(){
        return (
            <div id="claimPage">
                <ClaimForm/>
                <InvoiceForm/>
            </div>
        );
    }
}

export default ClaimPage;