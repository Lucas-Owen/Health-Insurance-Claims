import react from 'react';
import { Navigate } from 'react-router-dom';
import axios from 'axios';

import '../../styles/Login.css';
class Login extends react.Component {
    constructor(props){
        super();
        this.state = {
            user : {
                username: '',
                password: ''
            }
        };

        this.handleChange = this.handleChange.bind(this);
        this.loginPage = this.loginPage.bind(this);
        this.login = this.login.bind(this);
    }
    
    async login(){

        await axios.post('http://localhost:8080/login', this.state.user)
        .then(response =>{ 
            this.props.setToken(response.headers['authorization']);
        })
        .catch(error => console.log(error.message));
    }

    async handleChange(event){
        const {name, value} = event.target;
        await this.setState(oldState => {
            oldState.user[name] = value;
            return oldState;
        });

    }

    loginPage(){
        // TODO: Put this as its own component due to css overlaps...
        return (
            <div id='loginPage'>
                <div id='loginForm'>
                    <div className="inputField">
                        <label name='usernameLabel'>Username</label>
                        <input type='text' name='username' value={this.state.user.username} onChange={this.handleChange}/>
                    </div>
                    <div className="inputField">
                        <label name='passwordLabel'>Password</label>
                        <input type='password' name='password' value={this.state.user.password} onChange={this.handleChange}/>
                    </div>
                    <div className='centerContainer'>
                        <button onClick={this.login}>Login</button>
                    </div>
                </div>
            </div>
            );
    }

    render(){
        return this.props.state.token === null ? 
        <this.loginPage/> : <Navigate replace to="/provider/ClaimPage"/>;
    }

}

export default Login;