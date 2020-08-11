import React, {Component} from 'react';

import {Navbar, Nav} from 'react-bootstrap';
import {Container, Row, Col} from 'react-bootstrap';

export default class Footer extends Component {
	render() {
		let currYear = new Date().getFullYear();
		return (
			<Navbar fixed="bottom" bg="dark" variant="dark">
				<Container>
					<Row>
						<Col lg={12} className="text-center text-muted">
							<div>{currYear}-{currYear+1}, All Rights Reserved by React App E-Comm.</div>
						</Col>
					</Row>
				</Container>
			</Navbar>
		);
	}
}