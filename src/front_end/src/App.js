import "./App.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import TokenMain from "./pages/token/TokenMain";
import LogIn from "./pages/login/LogIn";
import SignIn from "./pages/SignIn";

function App() {
  return(

    <BrowserRouter>
      <Routes>
          <Route path = "signin" element={<SignIn/>} />
          <Route path="/login/" element={<LogIn/>} />
          <Route path="/token/" element={<TokenMain />} />
          <Route path="/token/:token" element={<TokenMain />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;
