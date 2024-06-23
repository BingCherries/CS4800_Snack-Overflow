import Navbar from "./Navbar"
import Login from "./Components/Login"
import Home from "./Components/Home"
import About from "./Components/About"
import Signup from "./Components/Signup"
import LogIngredients from './Components/LogIngredients';
import RecordReactions from './Components/RecordReactions';
import { Route, Routes } from "react-router-dom"
import "./CSS/App.css"
import "./CSS/login.css"
import "./CSS/Signup.css"
import "./CSS/LogIngredients.css"
import "./CSS/RecordReactions.css"

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
          <Route path="/log_ingredients" element={<LogIngredients />} />
          <Route path="/record_reactions" element={<RecordReactions />} />
        </Routes>
      </div>
    </>
  )
}

export default App;
