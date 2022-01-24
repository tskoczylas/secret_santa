import "./App.css";
import {BrowserRouter, HashRouter, Route, Routes} from "react-router-dom";
import LogIn from "./pages/LogIn";
import SignIn from "./pages/SignIn/SignIn";
import TokenMain from "./pages/token/TokenMain";
import AdminAuth from "./pages/admin/AdminAuth";
import {tokenAndUserAvailable} from "./data/DataService";

import SignInConformation from "./pages/SignIn/SignInConformation";
import MainPage from "./pages/admin/MainPage";

function App() {
  return(


    <HashRouter>
      <Routes>
          <Route path="/" element={<MainPage/>}/>
          <Route path="/admin/:page" element={tokenAndUserAvailable ?<LogIn/> : <AdminAuth/>}/>
          <Route path="/admin" element={tokenAndUserAvailable ?<LogIn/> : <AdminAuth/>}/>
          <Route path="/admin/confirm/:confirmToken" element={<SignInConformation/>}/>

          <Route path = "/signin" element={<SignIn/>} />
          <Route path="/login/" element={<LogIn/>} />
          <Route path="/token/" element={<TokenMain />} />
          <Route path="/token/:token" element={<TokenMain />} />
      </Routes>
    </HashRouter>

  );
}

export default App;
