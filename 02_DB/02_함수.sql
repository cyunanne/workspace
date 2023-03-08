-- 함수: 컬럼의 값을 읽어서 연산 결과를 반환
-- 단일행 함수(Single Row Function): N개의 행의 값을 전달하여 N개의 결과를 반환
-- 그룹 함수(Group Function): N개의 행의 값을 하나의 그룹으로 묶어 그룹 수 만큼의 결과를 반환
-- 함수는 SELECT 절, WHERE 절, ORDER BY 절, GROUP BY 절, HAVING 절 사용 가능

/************************************ 단일행 함수 ************************************/

-- <문자열 관련 함수>

-- LENGTH(문자열|컬럼명) : 문자열 길이 반환
SELECT 'HELLO WORLD', LENGTH('HELLO WORLD') FROM DUAL;

-- EMPLOYEE 테이블에서 이메일이 12글자인 사원의 이름, 이메일 조회
SELECT EMP_NAME, EMAIL
FROM EMPLOYEE 
WHERE LENGTH(EMAIL) = 12;

-- EMPLOYEE 테이블에서 이메일 길이 오름차순으로 조회
SELECT EMP_NAME, EMAIL
FROM EMPLOYEE
ORDER BY LENGTH(EMAIL) ASC;

-----------------------------------------------------------------------------------

-- INSTR(문자열|컬럼명, '찾을 문자열')
-- INSTR(문자열|컬럼명, '찾을 문자열', 찾을 시작 위치)
-- INSTR(문자열|컬럼명, '찾을 문자열', 찾을 시작 위치, 순번)
-- 찾을 시작 위치부터 지정된 순번째에 찾을 문자열의 시작 위치 반환

-- 문자열에서 맨 앞에 있는 B의 위치 조회
SELECT 'AABAACAABBAAC', INSTR('AABAACAABBAAC', 'B') FROM DUAL; -- 결과: 3

-- 문자열을 5번째 부터 검색하여 처음 찾는 B의 위치 조회
SELECT 'AABAACAABBAAC', INSTR('AABAACAABBAAC', 'B', 5) FROM DUAL; -- 결과: 9

-- 문자열을 5번째 부터 검색하여 두 번쨰로 찾는 B의 위치 조회
SELECT 'AABAACAABBAAC', INSTR('AABAACAABBAAC', 'B', 5, 2) FROM DUAL; -- 결과: 10

-- EMPLOYEE 테이블에서 사원명, 이메일, 이메일 중 '@' 위치 조회
SELECT EMP_NAME, EMAIL, INSTR(EMAIL, '@')
FROM EMPLOYEE;

-----------------------------------------------------------------------------------

-- SUBSTR(문자열|컬럼명, 시작위치)
-- SUBSTR(문자열|컬럼명, 시작위치, 길이)
-- 문자열을 시작 위치부터 지정된 길이 만큼(길이 미작성 시 끝까지) 잘라내서 반환
SELECT SUBSTR('ABCDEF', 3, 3) FROM DUAL; -- CDE
SELECT SUBSTR('ABCEDF', 3) FROM DUAL; -- CDEF

-- EMPLOYEE 테이블에서 사원명, 사원의 이메일 아이디만 조회 후 아이디로 오름차순 정렬
SELECT EMP_NAME, SUBSTR(EMAIL, 1, INSTR(EMAIL, '@')-1) 아이디
FROM EMPLOYEE
ORDER BY 아이디;

-----------------------------------------------------------------------------------

-- TRIM(문자열|컬럼명)
-- TRIM([옵션] 문자열|컬럼명 FROM 문자열|컬럼명)
-- [옵션]: LEADING(앞쪽), TRAILING(뒤쪽), BOTH(양쪽, 기본값)
-- 주어진 문자열의 앞|뒤|양쪽에 존재하는 지정된 문자열을 제거

SELECT '     K H     ',
	   TRIM(BOTH ' ' FROM '     K H     '),
	   TRIM(LEADING ' ' FROM '     K H     '),
	   TRIM(TRAILING ' ' FROM '     K H     ')
FROM   DUAL;

SELECT '#####K H#####',
	   TRIM(BOTH '#' FROM '#####K H#####'),
	   TRIM(LEADING '#' FROM '#####K H#####'),
	   TRIM(TRAILING '#' FROM '#####K H#####'),
FROM   DUAL;

-- 문자열 앞뒤 공백 제거, 해당 용도로 가장 많이 사용
SELECT TRIM('     K H     ') FROM DUAL;

-----------------------------------------------------------------------------------

-- REPLACE(문자열|컬럼명, 찾을 문자열, 바꿀 문자열)
-- 찾을 문자열을 바꿀 문자열로 변경하여 문자열 반환
SELECT REPLACE(NATIONAL_NAME, '한국', '대한민국') FROM NATIONAL;

-----------------------------------------------------------------------------------

-- <숫자 관련 함수>

-- MOD(숫자|컬럼명, 숫자|컬럼명) : 나머지 값 반환
SELECT EMP_NAME, MOD(SALARY, 1000000)
FROM   EMPLOYEE;

-- ABS(숫자|컬럼명) : 절댓값
SELECT ABS(10), ABS(-10) FROM DUAL;

-- CEIL(숫자|컬럼명)  : 올림
-- FLOOR(숫자|컬럼명) : 내림
-- 소숫점 첫째 자리에서 처리하여 정수 결과 반환
SELECT 123.5, CEIL(123.5), FLOOR(123.5) FROM DUAL;

-- ROUND(숫자|컬럼명) : 첫번째 소수점에서 반올림하여 정수 반환
-- ROUND(숫자|컬럼명, 소수점위치)
--  1) 소수점위치 > 0 : 소수점위치까지 출력
--  2) 소수점위치 < 0 : 정수 자리수에서 반올림
SELECT 123.456,
	   ROUND(123.456),     -- 123    (소수점 첫째 자리에서 반올림)
	   ROUND(123.456, 2),  -- 123.45 (소수점 둘째 자리까지 표현)
	   ROUND(123.456, 1),  -- 123.5  (소수점 첫째 자리까지 표현)
	   ROUND(123.456, 0),  -- 123    (소수점 0번째 자리까지 표현 -> 정수)
	   ROUND(123.456, -1), -- 120    (소수점 -1번째 자리까지 표현 = 1의 자리 반올림)
	   ROUND(123.456, -2)  -- 100    (소수점 -2번째 자리까지 표현 = 10의 자리 반올림)
FROM   DUAL;

-- TRUNC(숫자|컬럼명) : 잘라내기, 버림, 절삭
-- TRUNC(숫자|컬럼, 소수점 위치) : ROUND()랑 유사하게 작동
SELECT TRUNC(123.456),
	   TRUNC(123.456, 1),
	   TRUNC(123.456, -1)
FROM DUAL;

-- !주의 : 내림과 버림은 다르므로 잘 구분해서 사용하자.
SELECT FLOOR(-123.5), TRUNC(-123.5) FROM DUAL;

SELECT EMP_NAME, TRUNC(SALARY, -6) || '원 이상' 급여
FROM EMPLOYEE;

-----------------------------------------------------------------------------------

-- <날짜 관련 함수>

-- SYSDATE      : 시스템의 현재 시간(년,월,일,시,분,초) 반환
-- SYSTIMESTAMP : SYSDATE 반환값 + ms 단위 추가
SELECT SYSDATE, SYSTIMESTAMP FROM DUAL;
-- 2023-03-07 12:22:40.000	2023-03-07 12:22:40.688 +0900

-- MONTHS_BETWEEN(날짜, 날짜) : 두 날짜의 개월 수 차이를 반환
SELECT '약 ' || ROUND(MONTHS_BETWEEN('2023/07/14', '2023/02/06')) || '개월' 훈련기간 FROM DUAL;

-- EMPLOYEE 테이블에서 사원의 이름, 입사일, 근무 N년차, 근속 개월 수 조회
SELECT EMP_NAME, HIRE_DATE
    , CEIL(MONTHS_BETWEEN(SYSDATE, HIRE_DATE) / 12) "근무 N년차"
	, FLOOR(MONTHS_BETWEEN(SYSDATE, HIRE_DATE)) "근속 개월 수"
FROM EMPLOYEE;

-- ADD_MONTH(날짜, 숫자) : 날짜에 숫자 만큼의 개월 수를 더하여 반환
SELECT ADD_MONTHS(SYSDATE, -1), SYSDATE, ADD_MONTHS(SYSDATE, 1) FROM DUAL;

-- LAST_DAY(날짜) : 해당 월의 마지만 날짜를 반환
SELECT SYSDATE, LAST_DAY(SYSDATE), LAST_DAY('2023/04/01') + 1  FROM DUAL; -- 4월 마지막 날 + 1일

-- EXTRACT(YEAR|MONTH|DAY FROM 날짜) : 날짜에서 년|월|일을 추출하여 정수로 반환
-- EMPLOYEE 테이블에서 각 사원의 이름, 입사 년, 월, 일 조회 (단, 2000년 이상 입사자만 입사년도 오름차순 조회)
SELECT EMP_NAME, 
	   EXTRACT(YEAR  FROM HIRE_DATE) 년,
	   EXTRACT(MONTH FROM HIRE_DATE) 월,
	   EXTRACT(DAY   FROM HIRE_DATE) 일
FROM   EMPLOYEE
WHERE  EXTRACT(YEAR  FROM HIRE_DATE) >= 2000
ORDER BY 년;

-----------------------------------------------------------------------------------

-- <형 변환 함수>
-- 문자열(CHAR) <-> 숫자(NUMBER)
-- 문자열(CHAR) <-> 날짜(DATE)
-- 숫자(NUMBER) --> 날짜(DATE)

-- 숫자 -> 문자열
-- TO_CHAR(날짜|숫자) : 문자열로 변환
-- TO_CHAR(날짜|숫자, 포맷)
-- 포맷 1) 9 : 숫자 한 칸을 의미, 오른쪽 정렬, 빈 칸에 공백 추가
--     2) 0 : 숫자 한 칸을 의미, 오른쪽 정렬, 빈 칸에 0 추가
--     3) L : 현재 시스템이나 DB에 설정된 나라의 화폐기호
--     4) , : 숫자의 자릿수 구분
SELECT 1234,                -- 1234
	TO_CHAR(1234, '99999'), --  1234
	TO_CHAR(1234, '00000')  -- 01234
FROM DUAL;

SELECT 1000000,
	TO_CHAR(1000000, 'L9999999'),  -- \1000000
	TO_CHAR(1000000, '$9999999'),  -- $1000000
	TO_CHAR(1000000, '$9,999,999') -- $1,000,000
FROM DUAL;

-- EMPLOYEE 테이블에서 사번, 이름, 연봉 조회(포맷: \100,000,000)
SELECT EMP_ID, EMP_NAME, 
	--TO_CHAR(SALARY * 12, 'L9,999,999') 연봉 -- 숫자를 문자열로 바꿀 때 칸 수가 충분하지 않으면 '#'으로 채워짐
	TO_CHAR(SALARY * 12, 'L999,999,999')
FROM EMPLOYEE;

-- 날짜 -> 문자열
-- YY    : 연도(짧게) ex) 23
-- YYYY  : 연도(길게) ex) 2023
-- RR    : 연도(짧게) ex) 23
-- RRRR  : 연도(길게) ex) 2023
-- MM    : 월
-- DD    : 일
-- AM/PM : 오전/오후
-- HH    : 시간(12시간)
-- HH24  : 시간(24시간)
-- MI	 : 분
-- SS	 : 초
-- DAY   : 요일(전체) ex) 월요일, MONDAY (지역에 따라 다름)
-- DY	 : 요일(잛게) ex) 월, MON (지역에 따라 다름)
SELECT SYSDATE, TO_CHAR(SYSDATE, 'AM HH:MI:SS') FROM DUAL;
SELECT SYSDATE, TO_CHAR(SYSDATE, 'YYYY-MM-DD DAY HH24:MI:SS') FROM DUAL;

-- 포맷에 포함되지 않는 글자는 쌍따옴표("") 내부에 작성
-- 의도 : 2023년 03월 07일 화요일
SELECT TO_CHAR(SYSDATE, 'YYYY"년" MM"월" DD"일" DAY') TODAY FROM DUAL;

-- EMPLOYEE 테이블에서 모든 사원의 입사일을 '2023년 03월 07일 (화)' 형식으로 조회
SELECT EMP_NAME, TO_CHAR(HIRE_DATE, 'RRRR"년" MM"월" DD"일" (DY)') 입사일
FROM EMPLOYEE;

-- 문자열 -> 날짜
-- TO_DATE(문자열, 숫자)
-- TO_DATE(문자열, 숫자, 포맷)
-- 문자열이나 숫자를 지정된 포맷의 날짜 형식으로 해석하여 DATE 타입으로 반환
SELECT '2023-03-07', TO_DATE('2023-03-07') FROM DUAL;
SELECT TO_DATE('2023년 03월 07일', 'YYYY"년" MM"월" DD"일"') FROM DUAL;

-- 숫자 -> 날짜
SELECT  20230308, TO_DATE(20230308) FROM DUAL;

-- 날짜 패턴 Y, R의 차이점
-- 연도를 짧게 해석하는 경우 
--   1) 50 미만 : Y, R 모두 앞부분에 현재 세기(21c)를 적용
--   2) 50 이상 : Y-현재 세기(21c), R-이전 세기(20c) 적용

-- 의도 : 1945-01-15
SELECT TO_DATE('490115', 'YYMMDD'),
       TO_DATE('490115', 'RRMMDD')
FROM DUAL;

-- 의도 : 1950-01-15
SELECT TO_DATE('500115', 'YYMMDD'),
       TO_DATE('500115', 'RRMMDD')
FROM DUAL;

-- 문자열 -> 숫
-- TO_NUMBER(문자열)
-- TO_NUMBER(문자열, 포맷)
SELECT TO_NUMBER('$1,500', '$9,999') FROM DUAL;

-----------------------------------------------------------------------------------

-- <NULL 처리 함수>
-- DB에서 NULL과 연산하는 경우 모든 결과는 NULL

-- NVL(컬럼명, 컬럼값이 NULL일 경우 바꿀 값)
-- 컬럼값이 NULL일 경우 지정된 값으로 변경
-- 해당하는 컬럼과 데이터 타입이 일치하는 값으로만 대치 가능

-- EMPLOYEE 테이블에서 이름, 급여, 보너스, 급여 * 보너스 조회
SELECT EMP_NAME, SALARY, NVL(BONUS, '가나다'), SALARY * NVL(BONUS, 0)
FROM EMPLOYEE;

-- NVL2(컬럼명, NULL(X)인 경우 바꿀 값, NULL(O)인 경우 바꿀 값)

-- EMPLOYEE 테이블에서 기존에 보너스를 받은 사원은 보너스를 +0.2,
-- 보너스를 받지 못했던 사원은 0.3으로 변경
SELECT EMP_NAME, BONUS, NVL2(BONUS, BONUS + 0.2, 0.3)
FROM EMPLOYEE;




/************************************ 그룹 함수 ************************************/