import Start from './pages/Start';
import Main from './pages/Main';
import Signup from './pages/Signup';
import Login from './pages/Login';
import Consult from './pages/Consult/Consult';
import HTP from './pages/HTP/HTP';
import House from './pages/HTP/House';
import Tree from './pages/HTP/Tree';
import Person from './pages/HTP/Person';
import Mission from './pages/Mission/Mission';
import Postit from './pages/Postit/Postit';
import Mypage from './pages/Mypage/Mypage';
import { Routes, Route } from 'react-router-dom'

function App() {
  return (
    <div className="App select-none">
      <Routes>
        <Route path='/' Component={Start}/>
        <Route path='/main' Component={Main}/>
        <Route path='/signup' Component={Signup}/>
        <Route path='/login' Component={Login}/>
        <Route path='/htp' Component={HTP}>
          <Route path='house' Component={House}/>
          <Route path='tree' Component={Tree}/>
          <Route path='person' Component={Person}/>
        </Route>
        <Route path='/consult' Component={Consult}/>
        <Route path='/mission' Component={Mission}/>
        <Route path='/postit' Component={Postit}/>
        <Route path='/mypage' Component={Mypage}/>
        <Route path='*' element={<div>없는 페이지에요</div>}/>
      </Routes>
    </div>
  );
}

export default App;

