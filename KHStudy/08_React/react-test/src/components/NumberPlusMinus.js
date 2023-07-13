import React, { useState } from "react";

const NumberPlusMinus = () => {
  const [count, setCount] = useState(0);
  return (
    <>
      <button className="minus" onClick={ () => setCount(count - 1)}>-</button>
      {count}
      <button className="plus" onClick={ () => setCount(count + 1) }>+</button>
    </>
  );
}

export default NumberPlusMinus;