import react from 'react';
import axios from 'axios';

class Login extends react.Component {
    constructor(){
        super();
        this.state = {
            Authentication : '', 
            user : {
                username: '',
                password: ''
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.login = this.login.bind(this);
    }
    
    async login(){
        await axios.post('http://localhost:8080/provider/login', this.state.user)
        .then(response => console.log(response.data))
        .catch(error => console.log(error.message));
    }

    async handleChange(event){
        const {name, value} = event.target;
        await this.setState(oldState => {
            oldState.user[name] = value;
            return oldState;
        });

    }

    render(){
        return (
            <div>
                <div>
                    Username<input type='text' name='username' value={this.state.user.username} onChange={this.handleChange}/>
                </div>
                <div>
                    Password<input type='password' name='password' value={this.state.user.password} onChange={this.handleChange}/>
                </div>
                <button onClick={this.login}>Login</button>
            </div>
            );
    }

}

export default Login;