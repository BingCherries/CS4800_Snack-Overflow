import Navbar from "./Navbar"
import Login from "./Components/Login"
import Home from "./Components/Home"
import About from "./Components/About"
import Signup from "./Components/Signup"

import RecordReactions from './Components/RecordReactions';
import Dashboard from './Components/Dashboard';
import { Route, Routes } from "react-router-dom"
import "./CSS/App.css"



function App() {
  return (
    <>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />

          <Route path="/record_reactions" element={<RecordReactions />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </div>
    </>
  )
}

export default App;
