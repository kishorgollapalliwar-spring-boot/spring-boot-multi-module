import React from 'react';
import logo from './logo.svg';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';

import NavigationBar from './components/NavigationBar';
import Home from './components/Home';
import Footer from './components/Footer';
import Product from './components/Product';
import ProductItem from './components/ProductItem';

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
            <Product />
            <ProductItem />
          </Col>
        </Row>
      </Container>
      <Footer />
    </div>
  );
}

export default App;
