import "./App.css";
import UserPage from "./messaging/userpage/Userpage";
import Actionpage from "./creating/actions/Actionpage";

function App() {
  return (
    <div className="side-by-side">
      <div
        className="side-by-side-child
       bg-green-500"
      >
        <UserPage />
      </div>
      <div className="side-by-side-child bg-blue-400">
        <Actionpage />
      </div>
    </div>
  );
}

export default App;
