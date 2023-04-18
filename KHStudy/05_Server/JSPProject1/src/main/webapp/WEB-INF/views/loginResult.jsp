<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>
    <h1>로그인 결과</h1>
    <ul>
        <li>id : <%= request.getParameter("id") %></li>
        <li>pw : <%= request.getParameter("pw") %></li>
    </ul>
    <%-- JSP 주석 --%>
    <%-- Servlet에서 추가한 속성(message) 얻어오기 --%>
    <h1><%= request.getAttribute("message") %></h1>
    <button type="button" onclick="history.back()">돌아가기</button>
</body>
</html>