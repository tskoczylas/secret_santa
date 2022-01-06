import "./App.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import LogIn from "./pages/LogIn";
import SignIn from "./pages/SignIn";
import TokenMain from "./pages/token/TokenMain";
import Admin from "./pages/admin/Admin";
import AdminAuth from "./pages/admin/AdminAuth";
import {tokenAndUserAvailable} from "./data/DataService";
import NewGame from "./pages/admin/newGame/NewGame";
import Games from "./pages/admin/Games";
import Account from "./pages/admin/Account";
import FirstStep from "./pages/admin/newGame/FirstStep";
import Test from "./test";

function App() {
  return(


    <BrowserRouter>
      <Routes>
          <Route path="/admin" element={tokenAndUserAvailable ?<LogIn/> : <AdminAuth/>}/>
          <Route path = "/signin" element={<SignIn/>} />
          <Route path="/login/" element={<LogIn/>} />
          <Route path="/token/" element={<TokenMain />} />
          <Route path="/token/:token" element={<TokenMain />} />
          <Route path="/admin/new_game" element={<NewGame/>}/>
          <Route path="/admin/exist_games" element={<Games/>}/>
          <Route path="/admin/account" element={<Account/>}/>
          <Route path="/abc" element={<Test/>}/>
      </Routes>
    </BrowserRouter>

  );
}

export default App;
