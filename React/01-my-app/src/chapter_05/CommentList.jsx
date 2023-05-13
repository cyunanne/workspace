import React from "react";
import Comment from "./Comment";

const comments = [
    {
        name: "최유나",
        comment: "안녕하세요, 최유나입니다.",
    },
    {
        name: "자몽",
        comment: "나는야 사고뭉치 최자몽!!",
    },
    {
        name: "서은영",
        comment: "향수가 갖고싶으시답니다 ㅎ",
    },
];

function CommentList(props) {
    return (
        <div>
            {comments.map((comment) => {
                return (
                    <Comment name={comment.name} comment={comment.comment} />
                );
            })}
            
        </div>
    );
}

export default CommentList;