import "./App.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import TokenMain from "./pages/TokenMain";

function App() {
  return(

    <BrowserRouter>
      <Routes>
          <Route path="/" element={<TokenMain />} />
          <Route path="/:token" element={<TokenMain />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;
