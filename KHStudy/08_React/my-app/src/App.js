import { Fragment } from 'react';
import './App.css';
import Ex1 from './components/Exam1';
// components 폴더의 Exam1.js를 가져와서 사용
// 사용할 때 이름을 Ex1으로 지정
import Ex2 from './components/Exam2';
import PropsEx  from './components/R01_props';
import State1 from './components/R02_state1';
import State2 from './components/R03_state2';
import State3 from './components/R04_state3';
import TodoList1 from './components/R05_todolist1';
import ContextApi from './components/R06_context_api';

function App() {

  // 리액트의 컴포넌트는 딱 하나의 요소만을 반환할 수 있다
  // -> 여러 요소를 반환하고 싶을 때는 부모요소로 묶어준다!
  return (
    /* fragment(<> </>) : 반환되는 요소를 감쌀 때 사용, 해석되지 않음 */
    <>
      {/* jsx 주석 */} 
      {/* <h1>Hello React!!</h1>
      <p>ㅎ</p> */}

      {/* <Ex1 /> */}
      {/* <Ex2 /> */}

      {/* <PropsEx name={'홍길동'} />
      <PropsEx name={'최유나'} /> */}

      {/* <State1 />
      <State2 init={1} />
      <State3 /> */}

      {/* R05_TodoList1 */}
      <TodoList1 />
      <hr />

      {/* R06_context_api */}
      <ContextApi />
    </>
  );
}

export default App;
