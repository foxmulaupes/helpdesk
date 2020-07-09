import React from "react";
import User from '../User';
import ReactDOM from "react-dom";

function Form() {
  function Render(){
    return(
    ReactDOM.render(<User />, document.getElementById("root"))
    );
  }
  return (
    <form className="form">
      <input type="text" placeholder="Username" />
      <input type="password" placeholder="Password" />
      <button type="submit" onClick={Render}>Login</button>
    </form>
  );
}

export default Form;
