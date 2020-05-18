import React from "react";
import ReactDOM from "react-dom";
import App from "./components/App";

function Render(){
    return(
    ReactDOM.render(<App />, document.getElementById("root"))
    );
}

function Welcome() {
  return (
  <div class="welcome">
    <h1>Welcome to <span class="colored">Ticket Portal</span></h1>
    <button  id="Button" onClick={Render} class="button"><p id = "button">Login to Generate Ticket</p></button>
 </div>
  );
}

export default Welcome;
