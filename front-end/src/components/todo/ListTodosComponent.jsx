import React, { Component } from "react";
import TodoDataService from "../../api/todo/TodoDataService.js";
import AuthenticationService from "./AuthenticationService.js";

class ListTodosComponent extends Component {
  constructor(props) {
    console.log("constructor");
    super(props);
    this.state = {
      todos: [
        // {
        //   id: 1,
        //   description: "Learn React",
        //   done: false,
        //   targetDate: new Date(),
        // },
        // {
        //   id: 2,
        //   description: "Learn Spring Boot",
        //   done: false,
        //   targetDate: new Date(),
        // },
        // {
        //   id: 3,
        //   description: "Learn How States Work In React",
        //   done: false,
        //   targetDate: new Date(),
        // },
        // {
        //   id: 4,
        //   description: "Learn How React And Spring Boot Work Together",
        //   done: false,
        //   targetDate: new Date(),
        // },
        // {
        //   id: 5,
        //   description: "Be Awesome",
        //   done: false,
        //   targetDate: new Date(),
        // },
        // {
        //   id: 6,
        //   description: "Survive Corona Virus ",
        //   done: false,
        //   targetDate: new Date(),
        // },
      ],
    };
  }

  componentDidMount() {
    console.log("componentDidMount");
    let username = AuthenticationService.getLoggedInUserName();
    TodoDataService.retrieveAllTodos(username).then((response) => {
      // console.log(response);
      this.setState({ todos: response.data });
    });
  }

  render() {
    console.log("render");
    return (
      <div>
        <h1>List Todos</h1>
        <div className="container">
          <table className="table">
            <thead>
              <tr>
                <th>Description</th>
                <th>Target Date</th>
                <th>IsCompleted?</th>
              </tr>
            </thead>
            <tbody>
              {this.state.todos.map((todo) => (
                <tr key={todo.id}>
                  <td>{todo.description}</td>
                  <td>{todo.done.toString()}</td>
                  <td>{todo.targetDate.toString()}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    );
  }
}

export default ListTodosComponent;
