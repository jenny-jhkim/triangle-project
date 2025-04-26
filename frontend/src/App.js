import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home'
import TriangleRecord from './pages/TriangleRecord'
import Navbar from './components/Navbar';
import './App.css';

function App() {
  return (
      <div className="App">
        <Router>
          <Navbar />
          <Routes>
            <Route path="/" element={ <Home/>} />
            <Route path="/record" element={ <TriangleRecord/>} />
          </Routes>
        </Router>
      </div>
  );
}

export default App;
