import React, { Component } from "react";
import { Link } from "react-router-dom";
import HelloWorldService from "../../api/todo/HelloWorldService.js";
import { ErrorMessage } from "formik";

class WelcomeComponent extends Component {
  constructor(props) {
    super(props);
    this.retrieveWelcomeMessage = this.retrieveWelcomeMessage.bind(this);
    this.state = {
      welcomeMessage: "",
    };
    this.handleSuccessfulResponse = this.handleSuccessfulResponse.bind(this);
    this.handleErrorResponse = this.handleErrorResponse.bind(this);
  }
  render() {
    return (
      <>
        <h1>Welcome!</h1>
        <div className="container">
          Welcome {this.props.match.params.name}. You can manage your todos{" "}
          <Link to="/todos">here</Link>.
        </div>
        <div className="container">
          Click here to get customized welcome message. You can manage your
          <button
            onClick={this.retrieveWelcomeMessage}
            className="btn btn-success"
          >
            Get Welcome Message
          </button>
        </div>
        <div className="container">{this.state.welcomeMessage}</div>
      </>
    );
  }

  retrieveWelcomeMessage() {
    // HelloWorldService.executeHelloWorldService().then((response) =>
    //   this.handleSuccessfulResponse(response)
    // );
    // HelloWorldService.executeHelloWorldBeanService().then((response) =>
    //   this.handleSuccessfulResponse(response)
    // );

    HelloWorldService.executeHelloWorldPathVariableService(
      this.props.match.params.name
    )
      .then((response) => this.handleSuccessfulResponse(response))
      .catch((error) => this.handleErrorResponse(error));
  }

  handleSuccessfulResponse(response) {
    this.setState({ welcomeMessage: response.data.message });
  }

  handleErrorResponse(error) {
    console.log(error.response);
    let errorMessage = "";
    if (error.message) {
      errorMessage += error.message;
    }
    if (error.response && error.response.data) {
      errorMessage += error.message;
    }
    this.setState({ welcomeMessage: errorMessage });
  }
}

export default WelcomeComponent;
