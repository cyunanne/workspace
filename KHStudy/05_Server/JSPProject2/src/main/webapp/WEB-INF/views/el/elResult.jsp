<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="edu.kh.jsp.model.dto.Book"%> Book 클래스 임포트(JSP 표현식 쓸 때 사용, EL은 필요없음) --%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EL 테스트 결과</title>
</head>
<body>
    <h1>EL을 이용해서 출력하기</h1>
    <h3>파라미터</h3>
    <pre>
        <ul>
            <li>\${param.name속성값} : request에 담긴 파라미터 얻어오기(1개)</li>
            <li>\${paramValues} : 모든 파라미터를 배열로 얻어오기</li>
            <li>\${paramValues.name속성값[index]} : name이 일치하는 파라미터 중 지정된 index 번째 value</li>
        </ul>

        <p>
            이름 : ${param.inputName}
            나이 : ${param.inputAge}
            opt : ${param.opt}
            opt : ${paramValues}
            opt : ${paramValues.opt}
            opt : ${paramValues.opt[2]}
        </p>
    </pre>

    <hr>

    <h3>세팅된 속성(attribute) 출력하기</h3>
    <ul>
        <li>기본 : \${key} (key는 세팅한 속성의 key값)</li>
        <li>배열/List : \${key[index]}</li>
        <li>DTO/MAP : \${key.필드명 또는 K}</li>
    </ul>

    <p>
        주소(JSP표현식) : <%=request.getAttribute("address")%><br>
        주소(EL) : ${address}<br>

        score : ${score}<br>
        strList : ${strList}<br>
        book : ${book}<br>

        <br><br>
        strList[0] : ${strList[0]}<br>
        strList[1] : ${strList[1]}<br>
        strList[2] : ${strList[2]}<br>
        strList[3] : ${strList[3]} (3번 인덱스 값 없음 -> 예외 발생하지 않고 빈칸으로 출력됨)<br>

        <br><br>

        <%-- book의 title 필드(jsp 표현식) : <%=((Book)(request.getAttribute("book"))).getTitle()%> --%>
        <br>
        <%-- EL은 import가 필요 없음 --%>
        book의 title 필드(EL) : ${book.title}<br>
        book의 writer 필드(EL) : ${book.writer}<br>
        book의 price 필드(EL) : ${book.price}<br>
    </p>

    <hr>

    <h1>EL은 null, 비어있다를 같은 것으로 생각한다</h1>
    ${list1}(null) / ${list2}(빈 리스트)

    <h4>empty 연산자</h4>
    ${empty list1}(null) / ${empty list2}(빈 리스트)
    
</body>
</html>