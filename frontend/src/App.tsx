import React from 'react';
import Start from './pages/Start';
import Main from './pages/Main';
import Signup from './pages/Signup';
import Login from './pages/Login';
import Consult from './pages/Consult/Consult';
import HTP from './pages/HTP/HTP';
import Mission from './pages/Mission/Mission';
import Postit from './pages/Postit/Postit';
import Mypage from './pages/Mypage/Mypage';
import { Routes, Route } from 'react-router-dom'
import './App.css'
import { Button } from '@nextui-org/react';

function App() {
  return (
    <div className="App">
      <Button>테스트버튼</Button>
      <Routes>
        <Route path='/' Component={Start}/>
        <Route path='/main' Component={Main}/>
        <Route path='/signup' Component={Signup}/>
        <Route path='/login' Component={Login}/>
        <Route path='/htp' Component={HTP}/>
        <Route path='/consult' Component={Consult}/>
        <Route path='/mission' Component={Mission}/>
        <Route path='/postit' Component={Postit}/>
        <Route path='/mypage' Component={Mypage}/>
      </Routes>
    </div>
  );
}

export default App;

