<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSTL 조건문</title>
</head>
<body>
    <h1>4. 조건문 (c:if)</h1>
    <pre>
        - 단독 if무문 (c:else 없음!)
        - 속성은 test만 존재함

        ** 주의사항
        1) test의 속성값은 무조건 EL 구문으로 작성해야 한다
        2) test의 속성값은 true 또는 false 가 나오는 조건식이어야 한다
        3) test의 속성값을 작성하는 쌍따옴표("") 내부에는 앞뒤 공백이 존재해서는 안된다
    </pre>

    request scope 에 저장된 name : ${name} <br> <%-- 홍길동 --%>
    request scope 에 저장된 money : ${money} <br> <%-- 50000 --%>
    <br>
    <c:if test="${money == 40000}" >
        <h3 style="color: gold">돈이 5만원이 있어요!</h3>
    </c:if>

    <h3>EL에서 모든(문자열 포함) 비교는 == 또는 eq 사용 <-> != 또는 ne 사용</h3>
    <h3>EL에서 문자열을 홑따옴표('')로 표현</h3>
    <c:if test="${name ne '고길동'}" >
        <h3 style="color: red">이름이 일치합니다</h3>
    </c:if>

    <hr>

    <h1>5. 조건문 - choose, when, otherwise(if ~ else if ~ else)</h1>
    <pre>
        choose : when, otherwise 태그를 감싸는 태그
        when : if, else if 역할의 태그. 속성은 test만 있음
        otherwise : else 역할, 속성 없음

    </pre>

    <%-- 
        * queryString : 주소에 작성된 매개변수 문자열
        http://localhost/jstl/condition?val=777
        -> { "val" : 777 } 데이터 전달
    --%>
    <%--
        lt(less than)        : < 미만
        gt(greater than)     : > 초과
        le(less or equal)    : <= 이하
        gt(greater or equal) : >= 이상

        꺽쇄가 태그와 충돌을 일으키거나 헷갈릴 수 있으므로 기호 대신 알파벳을 사용할 수 있다
    --%>
    <c:choose>
        <c:when test="${param.val gt 100}">
            100 초과
        </c:when>

        <c:when test="${param.val < 100}" >
            100 미만
        </c:when>

        <c:otherwise>
            100과 같다
        </c:otherwise>
    </c:choose>
</body>
</html>