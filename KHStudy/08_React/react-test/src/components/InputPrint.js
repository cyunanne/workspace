import React, { useState } from "react";

const InputPrint = () => {
  const [inputText, setInputText] = useState("");
  const [text, setText] = useState("");
  return (
    <>
      <input onChange={e => setInputText(e.target.value)}></input> <button onClick={ () => setText(inputText) }>보내기</button>
      <div className="container"><h2>{text}</h2></div>
    </>
  );
};

export default InputPrint;