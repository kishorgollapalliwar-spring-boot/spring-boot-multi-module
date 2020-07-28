import React from 'react';
import {Navbar, Nav} from 'react-bootstrap';


class NavigationBar extends React.Component {
	render() {
		return (
			<div>
				<Navbar bg="dark" variant="dark">
					<Navbar.Brand href="/">React App E-Comm</Navbar.Brand>
					<Nav className="mr-auto">
						<Nav.Link href="/product">Product</Nav.Link>
						<Nav.Link href="/item">Item</Nav.Link>
					</Nav>
				</Navbar>
			</div>
		);
	}
}

export default NavigationBar;