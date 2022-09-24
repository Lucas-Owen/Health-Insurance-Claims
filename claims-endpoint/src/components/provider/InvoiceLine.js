import react from 'react';

class InvoiceLine extends react.Component {
    constructor(props){
        super();
        this.state = {
            onClickTr : props.onClickTr,
            onClickRemove : props.removeBtnClick,
            lines : props.lines
        }
    }

    render(){
        return (
            <tr onClick={this.state.onClickTr}>
                <td>{this.state.lines.category}</td>
                <td>{this.state.lines.description}</td>
                <td>{this.state.lines.pricePerUnit}</td>
                <td>{this.state.lines.quantity}</td>
                <td>{this.state.lines.cost}</td>
                <td><button onClick={(event) => {event.stopPropagation(); return this.state.onClickRemove();}}>Remove Line</button></td>
            </tr>
        )
    }
}

export default InvoiceLine;