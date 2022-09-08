import './App.css';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

import NavBar from './components/provider/ProviderNavBar';
import ClaimPage from './components/provider/ClaimPage';

function App() {
  return (
    <Router>
      <NavBar/>
      <Routes>
        <Route path='/provider/claimPage' element={<ClaimPage/>}/>
      </Routes>
    </Router>
  );
}

export default App;
