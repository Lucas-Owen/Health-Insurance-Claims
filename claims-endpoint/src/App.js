import './App.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import react from 'react';

// import NavBar from './components/provider/ProviderNavBar';
import ClaimPage from './components/provider/ClaimPage';
import Login from './components/provider/Login';

class App extends react.Component {
  constructor(){
    super();
    this.state = {token:null}
    this.setToken = this.setToken.bind(this);
  }

  async setToken(newToken){
      this.setState(oldState => {
          oldState.token = newToken;
          return oldState;
      });
  }

  render(){
    return (
      <Router>
        <Routes>
          <Route path='/provider/claimPage' element={<ClaimPage state={this.state}/>}/>
          <Route path='/provider/Login' element={<Login state={this.state} setToken={this.setToken}/>}/>
        </Routes>
      </Router>
    );
  }
}

export default App;
