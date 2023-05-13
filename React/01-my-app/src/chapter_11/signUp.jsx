import React, {useState} from "react";

function SignUp(porps) {
    const [name, setName] = useState("");
    const [gender, setGender] = useState("여");

    const handleChangeName = (e) => {
        setName(e.target.value);
    };

    const handleChangeGender = (e) => {
        setGender(e.target.value);
    };

    const handleSubmit = (e) => {
        alert(`이름: ${name}(${gender})`);
        e.preventDefault();
    };

    return (
        <form onSubmit={handleSubmit}>
            <label>
                이름:
                <input type="text" value={name} onChange={handleChangeName} />
            </label>
            <br />
            <label htmlFor="">
                성별:
                <select value={gender} onChange={handleChangeGender}>
                    <option value="남">남성</option>
                    <option value="여">여성</option>
                </select>
            </label>
            <br />
            <button>제출</button>
        </form>
    );
}

export default SignUp;