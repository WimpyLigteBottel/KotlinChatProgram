import "./App.css";
import UserPage from "./messaging/userpage/Userpage";
import Actionpage from "./creating/actions/Actionpage";

function App() {
  return (
    <div className="side-by-side">
      <div className="side-by-side-child">
        <UserPage />
      </div>
      <div className="side-by-side-child">
        <Actionpage />
      </div>
    </div>
  );
}

export default App;
