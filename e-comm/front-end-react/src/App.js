import React from 'react';
import logo from './logo.svg';
import './App.css';

import {Container, Row, Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom';

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
    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} style={marginTop}>
            <Switch>
              <Route path="/" exact component={Home} />
              <Route path="/product" exact component={Product} />
              <Route path="/product-item" exact component={ProductItem} />
            </Switch>
          </Col>
        </Row>
      </Container>
      <Footer />
    </Router>
  );
}

export default App;
