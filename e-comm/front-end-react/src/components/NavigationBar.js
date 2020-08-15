import React, {Component} from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom';


export default class NavigationBar extends Component {
	render() {
		return (
			<div>
				<Navbar bg="dark" variant="dark">
					<Link to={""} className="navbar-brand">
						React App E-Comm
					</Link>
					<Navbar.Brand href="/"></Navbar.Brand>
					<Nav className="mr-auto">
						<Link to={"product"} className="nav-link">Product</Link>
						<Link to={"product-item"} className="nav-link">Item</Link>
					</Nav>
				</Navbar>
			</div>
		);
	}
}