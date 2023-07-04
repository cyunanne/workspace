import React, { useState } from "react";

const Id = ({handler}) => {

  // console.log(handler); // props로 전달한 값 중 key가 handler인 요소의 value 반환

  return (
    <>
      <div className="Wrapper">
          <label htmlFor="id">ID : </label>
          <input type="text" id="id" onChange={handler}/>
      </div>
    </>
  );
};

const Pw = (props) => {
  return (
    <>
      <div className="Wrapper">
          <label htmlFor="pw">PW : </label>
          <input type="password" id="pw" onChange={props.handler}/>
      </div>
    </>
  );
};

const StateLiftingUp = () => {

  const [id, setId] = useState('');
  const [pw, setPw] = useState('');

  // id 값 변경 함수
  const idHandler = e => {
    setId(e.target.value);
  }

  // pw 값 변경 함수
  const pwHandler = e => {
    setPw(e.target.value);
  }

  console.log("id : " + id);
  console.log("pw : " + pw);

  return(
    <>
      <Id handler={idHandler}/>
      <Pw handler={pwHandler}/>
      <button disabled={id.length === 0 || pw.length === 0}>로그인</button>
    </>
  );
};

export default StateLiftingUp;