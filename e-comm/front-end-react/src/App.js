import React from 'react';
import logo from './logo.svg';
import './App.css';
import NavigationBar from './components/NavigationBar';
import Home from './components/Home';
import {Container, Row, Col} from 'react-bootstrap';

function App() {
  const marginTop = {
    marginTop:"2.5%"
  };

  return (
    <div className="App">
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Home />
          </Col>
        </Row>
      </Container>
    </div>
  );
}

export default App;
