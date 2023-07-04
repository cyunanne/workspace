import React, { useState } from "react";

const State2 = (props) => {
    // const [count, setCount] = useState(0);
    // useState : 컴포넌트의 상태를 관리할 때 상요하는 Hook
    // const [변수, 값을 변경하는 함수(setter)] = useState(초기값);
    
    const [count, setCount] = useState(props.init);

    return (
      <div>
        <h3>{count}</h3>
        <button onClick={ () => setCount(count+1) }>클릭하면 count 1 증가</button>
      </div>
    );
}

export default State2;