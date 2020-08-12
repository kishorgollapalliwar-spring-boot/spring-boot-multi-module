import React, {Component} from 'react';

import {Jumbotron} from 'react-bootstrap';

export default class Home extends Component {
	render() {
		return (
            <Jumbotron className="bg-dark text-white">
              <h1>Welcome to React App E-Comm!</h1>
              <blockquote className="blockquote mb-0">
              	<p>
              		Any fool can write code that a computer can understand. Good programmers write code that humans can understand.
              	</p>
              	<footer className="blockquote-footer">
              		Martin Fowler
              	</footer>
              </blockquote>
            </Jumbotron>
		);
	}
}