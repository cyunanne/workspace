import React, { useState } from "react";

// 리액트는 컴포넌트의 상태가 변할 때 마다 리렌더링을 수행 함
const InputTest = () => {

  // let inputValue = '초기값';
  
  const [inputValue, setInputValue] = useState("초기값");
          // 변수       // 함수
  // inputValue : 값을 저장하는 변수
  // setInputValue : inputValue에 값을 대입하는 setter 역할 함수

  const changeInputValue = e => {
    console.log(e.target.value);
    setInputValue(e.target.value);
  }

  return (
    // <input type="text" value={inputValue} 
    //   onChange={ e => setInputValue(e.target.value) }/>
  
    // 최초 렌더링 시 : value = "초기값"
    // => input 값 변경
    //   1) onChange(값이 변했을 때 -> changeInputValue 함수 실행 -> inputValue = e.target.value(변화된 값) 대입)
    //   2) 컴포넌트의 상태 변화 -> 리렌더링
    // 리 렌더링 이후 : value = 변경된 inputValue 값
    <input type="text" value={inputValue}
      onChange={changeInputValue} />
  );
};

export default InputTest;