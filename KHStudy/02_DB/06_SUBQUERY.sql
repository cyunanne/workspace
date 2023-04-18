/*
    * SUBQUERY (서브쿼리)
    - 하나의 SQL문 안에 포함된 또다른 SQL문
    - 메인쿼리(기존쿼리)를 위해 보조 역할을 하는 쿼리문
    -- SELECT, FROM, WHERE, HAVGIN 절에서 사용가능

*/  

-- 서브쿼리 예시 1.
-- 부서코드가 노옹철사원과 같은 소속의 직원의 
-- 이름, 부서코드 조회하기

-- 1) 사원명이 노옹철인 사람의 부서코드 조회
SELECT DEPT_CODE
FROM EMPLOYEE
WHERE EMP_NAME = '노옹철';

-- 2) 부서코드가 D9인 직원을 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = 'D9';

-- 3) 부서코드가 노옹철사원과 같은 소속의 직원 명단 조회   
--> 위의 2개의 단계를 하나의 쿼리로!!! --> 1) 쿼리문을 서브쿼리로!!
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE = (
	SELECT DEPT_CODE
	FROM EMPLOYEE
  	WHERE EMP_NAME = '노옹철');
				  
-- 서브쿼리 예시 2.
-- 전 직원의 평균 급여보다 많은 급여를 받고 있는 직원의 
-- 사번, 이름, 직급코드, 급여 조회

-- 1) 전 직원의 평균 급여 조회하기
SELECT AVG(SALARY)
FROM EMPLOYEE;

-- 2) 직원들중 급여가 3047663원 이상인 사원들의 사번, 이름, 직급코드, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= 3047663;

-- 3) 전 직원의 평균 급여보다 많은 급여를 받고 있는 직원 조회
--> 위의 2단계를 하나의 쿼리로 가능하다!! --> 1) 쿼리문을 서브쿼리로!!
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY >= (SELECT AVG(SALARY) FROM EMPLOYEE);
                 


-------------------------------------------------------------------

/*  서브쿼리 유형

    - 단일행 서브쿼리 : 서브쿼리의 조회 결과 값의 개수가 1개일 때 (행1, 열1)
    
    - 다중행 서브쿼리 : 서브쿼리의 조회 결과 값의 개수가 여러개일 때 (행N, 열1)
    
    - 다중열 서브쿼리 : 서브쿼리의 SELECT 절에 자열된 항목수가 여러개 일 때 (행1, 열N)
    
    - 다중행 다중열 서브쿼리 : 조회 결과 행 수와 열 수가 여러개일 때 (행N, 열N)
    
    - 상관 서브쿼리 : 서브쿼리가 만든 결과 값을 메인 쿼리가 비교 연산할 때 
                   메인 쿼리 테이블의 값이 변경되면 서브쿼리의 결과값도 바뀌는 서브쿼리
                     
    - 스칼라 서브쿼리 : 상관 쿼리이면서 결과 값이 하나인 서브쿼리
    
   * 서브쿼리 유형에 따라 서브쿼리 앞에 붙은 연산자가 다름
    
*/


-- 1. 단일행 서브쿼리 (SINGLE ROW SUBQUERY)
--    서브쿼리의 조회 결과 값의 개수가 1개인 서브쿼리 (행1, 열1)
--    단일행 서브쿼리 앞에는 비교 연산자 사용
--    <, >, <=, >=, =, !=/^=/<>


-- 전 직원의 급여 평균보다 많은 급여를 받는 직원의 
-- 이름, 직급명, 부서명, 급여를 직급 순으로 정렬하여 조회
SELECT EMP_NAME, JOB_NAME, DEPT_TITLE, SALARY
FROM EMPLOYEE
NATURAL JOIN JOB
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE SALARY >= (SELECT AVG(SALARY) FROM EMPLOYEE)
ORDER BY JOB_CODE;


-- 가장 적은 급여를 받는 직원의
-- 사번, 이름, 직급코드, 부서코드, 급여, 입사일을 조회
SELECT EMP_ID, EMP_NAME, JOB_CODE, DEPT_CODE, SALARY, HIRE_DATE
FROM EMPLOYEE
WHERE SALARY = (SELECT MIN(SALARY) FROM EMPLOYEE);
                 
-- 노옹철 사원의 급여보다 많이 받는 직원의 
-- 사번, 이름, 부서코드, 직급코드, 급여를 조회
-- #1
SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY > (SELECT SALARY FROM EMPLOYEE WHERE EMP_NAME = '노옹철');

-- #2
-- 메인쿼리 + 서브쿼리(where) + 서브쿼리(from)
-- (복잡하지만 신뢰도있는 정보 조회 가능)

-- (1) 사번을 급여 오름차순으로 정렬하여 조회
SELECT EMP_ID FROM EMPLOYEE ORDER BY SALARY;

-- (2) 급여가 가장 적은 사람의 사번 조회
-- ROWNUM : 행번호를 출력하는 가상의 컬럼
--          (주의사항) ORDER BY절 해석 전에 먼저 행번호 부여
SELECT EMP_ID
FROM (SELECT EMP_ID FROM EMPLOYEE ORDER BY SALARY)
WHERE ROWNUM=1;

-- (3) 급여가 가장 적은 사람의 사번을 이용해서 직원 정보 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, SALARY
FROM EMPLOYEE
WHERE EMP_ID = (
	SELECT EMP_ID
	FROM (
		SELECT EMP_ID 
		FROM EMPLOYEE
		ORDER BY SALARY)
	WHERE ROWNUM=1);

-- 부서별(부서가 없는 사람 포함) 급여의 합계 중 가장 큰 부서의
-- 부서명, 급여 합계를 조회 

-- 1) 부서별 급여 합 중 가장 큰값 조회
SELECT MAX(SUM(SALARY))
FROM EMPLOYEE
GROUP BY DEPT_CODE;

-- 2) 부서별 급여합이 17700000인 부서의 부서명과 급여 합 조회
SELECT DEPT_TITLE, SUM(SALARY) --, DEPT_CODE --> ORA-00979: GROUP BY 표현식이 아닙니다.
--> SELECT절에 작성된 컬럼(그룹함수 외)을 모두 GROUP BY절에 작성해야 그룹이 구성된다.
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_ID=DEPT_CODE)
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) = 17700000;

-- 3) >> 위의 두 서브쿼리 합쳐 부서별 급여 합이 큰 부서의 부서명, 급여 합 조회
SELECT DEPT_TITLE, SUM(SALARY)
FROM EMPLOYEE
JOIN DEPARTMENT ON (DEPT_ID=DEPT_CODE)
GROUP BY DEPT_TITLE
HAVING SUM(SALARY) = (
	SELECT MAX(SUM(SALARY))
	FROM EMPLOYEE
	GROUP BY DEPT_CODE);

-------------------------------------------------------------------------

-- 2. 다중행 서브쿼리 (MULTI ROW SUBQUERY)
--    서브쿼리의 조회 결과 값의 개수가 여러행일 때 

/*
    >> 다중행 서브쿼리 앞에는 일반 비교연산자 사용 x
    
    - IN / NOT IN : 여러 개의 결과값 중에서 한 개라도 일치하는 값이 있다면
                    혹은 없다면 이라는 의미(가장 많이 사용!)
    - > ANY, < ANY : 여러개의 결과값 중에서 한개라도 큰 / 작은 경우
                     가장 작은 값보다 큰가? / 가장 큰 값 보다 작은가?
    - > ALL, < ALL : 여러개의 결과값의 모든 값보다 큰 / 작은 경우
                     가장 큰 값 보다 큰가? / 가장 작은 값 보다 작은가?
    - EXISTS / NOT EXISTS : 값이 존재하는가? / 존재하지 않는가?
    
*/

-- 부서별 최고 급여를 받는 직원의 
-- 이름, 직급, 부서, 급여를 부서 순으로 정렬하여 조회

-- (1) 부서별 최고 급여 조회
SELECT MAX(SALARY) FROM EMPLOYEE GROUP BY DEPT_CODE; -- 7행 1열(다중행)

-- (2) 부서별 최고 급여자 정보 조회
SELECT EMP_NAME, JOB_CODE, DEPT_CODE, SALARY
FROM EMPLOYEE
WHERE SALARY IN (SELECT MAX(SALARY) FROM EMPLOYEE GROUP BY DEPT_CODE)
ORDER BY DEPT_CODE;

-- 사수에 해당하는 직원(MANAGER_ID=NULL)에 대해 조회 
--  사번, 이름, 부서명, 직급명, 구분(사수 / 직원)

-- 1) 사수에 해당하는 사원 번호 조회
SELECT DISTINCT MANAGER_ID 
FROM EMPLOYEE 
WHERE MANAGER_ID IS NOT NULL;

-- 2) 직원의 사번, 이름, 부서명, 직급명 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
LEFT JOIN JOB USING(JOB_CODE);

-- 3) 사수에 해당하는 직원에 대한 정보 추출 조회 (이때, 구분은 '사수'로)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사수' AS 구분
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
LEFT JOIN JOB USING(JOB_CODE)
WHERE EMP_ID IN (
	SELECT DISTINCT MANAGER_ID 
	FROM EMPLOYEE 
	WHERE MANAGER_ID IS NOT NULL);

-- 4) 일반 직원에 해당하는 사원들 정보 조회 (이때, 구분은 '사원'으로)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사원' AS 구분
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
LEFT JOIN JOB USING(JOB_CODE)
WHERE EMP_ID NOT IN (
	SELECT DISTINCT MANAGER_ID 
	FROM EMPLOYEE 
	WHERE MANAGER_ID IS NOT NULL);
            
-- 5) 3, 4의 조회 결과를 하나로 합침 -> SELECT절 SUBQUERY
-- * SELECT 절에도 서브쿼리 사용할 수 있음

-- #1 UNION ... 느림
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사수' AS 구분
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
LEFT JOIN JOB USING(JOB_CODE)
WHERE EMP_ID IN (
	SELECT DISTINCT MANAGER_ID 
	FROM EMPLOYEE 
	WHERE MANAGER_ID IS NOT NULL)	
UNION
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, '사원' AS 구분
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
LEFT JOIN JOB USING(JOB_CODE)
WHERE EMP_ID NOT IN (
	SELECT DISTINCT MANAGER_ID 
	FROM EMPLOYEE 
	WHERE MANAGER_ID IS NOT NULL);

-- #2 선택함수(CASE)로 풀이 ... #1보다 빠름
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME, 
	CASE 
		WHEN EMP_ID IN (
			SELECT DISTINCT MANAGER_ID 	
			FROM EMPLOYEE 
			WHERE MANAGER_ID IS NOT NULL)
		THEN '사수'
		ELSE '사원'
	END AS 구분
FROM EMPLOYEE 
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
JOIN JOB USING(JOB_CODE)
ORDER BY EMP_ID;

-- 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원의
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, > ANY 혹은 < ANY 연산자를 사용하세요

-- > ANY, < ANY : 여러개의 결과값 중에서 하나라도 큰 / 작은 경우
--                가장 작은 값보다 큰가? / 가장 큰 값 보다 작은가?

-- 1) 직급이 대리인 직원들의 사번, 이름, 직급명, 급여 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '대리';

-- 2) 직급이 과장인 직원들 급여 조회
SELECT SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '과장';

-- 3) 대리 직급의 직원들 중에서 과장 직급의 최소 급여보다 많이 받는 직원
-- 3-1) MIN을 이용하여 단일행 서브쿼리를 만듦.
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '대리'
AND SALARY > (
	SELECT MIN(SALARY)
	FROM EMPLOYEE
	JOIN JOB USING(JOB_CODE)
	WHERE JOB_NAME = '과장');

-- 3-2) ANY를 이용하여 과장 중 가장 급여가 적은 직원 초과하는 대리를 조회
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '대리'
AND SALARY > ANY (
	SELECT SALARY
	FROM EMPLOYEE
	JOIN JOB USING(JOB_CODE)
	WHERE JOB_NAME = '과장');

-- 차장 직급의 급여의 가장 큰 값보다 많이 받는 과장 직급의 직원
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, > ALL 혹은 < ALL 연산자를 사용하세요
-- > ALL, < ALL : 여러개의 결과값의 모든 값보다 큰 / 작은 경우
--                가장 큰 값 보다 크냐? / 가장 작은 값 보다 작냐?
SELECT EMP_ID, EMP_NAME, JOB_NAME, SALARY
FROM EMPLOYEE
JOIN JOB USING(JOB_CODE)
WHERE JOB_NAME = '과장'
AND SALARY > ALL (
	SELECT SALARY
	FROM EMPLOYEE
	JOIN JOB USING(JOB_CODE)
	WHERE JOB_NAME = '차장');
                      
                      
-- 서브쿼리 중첩 사용(응용편!)


-- LOCATION 테이블에서 NATIONAL_CODE가 KO인 경우의 LOCAL_CODE와
-- DEPARTMENT 테이블의 LOCATION_ID와 동일한 DEPT_ID가 
-- EMPLOYEE테이블의 DEPT_CODE와 동일한 사원을 구하시오.

-- 1) LOCATION 테이블을 통해 NATIONAL_CODE가 KO인 LOCAL_CODE 조회
SELECT LOCAL_CODE
FROM LOCATION
WHERE NATIONAL_CODE = 'KO'; -- 'L1' (단일행)

-- 2)DEPARTMENT 테이블에서 위의 결과와 동일한 LOCATION_ID를 가지고 있는 DEPT_ID를 조회
SELECT DEPT_ID
FROM DEPARTMENT
WHERE LOCATION_ID = (
	SELECT LOCAL_CODE
	FROM LOCATION
	WHERE NATIONAL_CODE = 'KO'); -- 5행 (다중행)

-- 3) 최종적으로 EMPLOYEE 테이블에서 위의 결과들과 동일한 DEPT_CODE를 가지는 사원을 조회
SELECT EMP_NAME, DEPT_CODE
FROM EMPLOYEE
WHERE DEPT_CODE IN (
	SELECT DEPT_ID
	FROM DEPARTMENT
	WHERE LOCATION_ID = (
		SELECT LOCAL_CODE
		FROM LOCATION
		WHERE NATIONAL_CODE = 'KO'));
--> 한국에서 근무하는 사원 조회됨
	                      
-----------------------------------------------------------------------

-- 3. 다중열 서브쿼리 (단일행 = 결과값은 한 행)
--    서브쿼리 SELECT 절에 나열된 컬럼 수가 여러개 일 때

-- 퇴사한 여직원과 같은 부서, 같은 직급에 해당하는
-- 사원의 이름, 직급, 부서, 입사일을 조회        

-- 1) 퇴사한 여직원 조회
SELECT EMP_NAME, JOB_NAME, DEPT_TITLE, HIRE_DATE
FROM EMPLOYEE 
NATURAL JOIN JOB
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE ENT_YN = 'Y'
AND SUBSTR(EMP_NO,8,1) = '2'; 

-- 2) 퇴사한 여직원과 같은 부서, 같은 직급 (다중 열 서브쿼리)
SELECT EMP_NAME, JOB_NAME, DEPT_TITLE, HIRE_DATE
FROM EMPLOYEE
NATURAL JOIN JOB 
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE (DEPT_TITLE, JOB_NAME) = (
	SELECT DEPT_TITLE, JOB_NAME
	FROM EMPLOYEE 
	NATURAL JOIN JOB
	JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
	WHERE ENT_YN = 'Y'
	AND SUBSTR(EMP_NO,8,1) = '2');

-------------------------- 연습문제 -------------------------------
-- 1. 노옹철 사원과 같은 부서, 같은 직급인 사원을 조회하시오. (단, 노옹철 사원은 제외)
--    사번, 이름, 부서코드, 직급코드, 부서명, 직급명
SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
NATURAL JOIN JOB 
JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)
WHERE (DEPT_CODE, JOB_CODE) = (
	SELECT DEPT_CODE, JOB_CODE
	FROM EMPLOYEE
	WHERE EMP_NAME = '노옹철')
AND EMP_NAME != '노옹철';

-- 2. 2000년도에 입사한 사원의 부서와 직급이 같은 사원을 조회하시오
--    사번, 이름, 부서코드, 직급코드, 고용일
SELECT EMP_ID, EMP_NAME, DEPT_CODE, JOB_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (DEPT_CODE, JOB_CODE) = (
	SELECT DEPT_CODE, JOB_CODE
	FROM EMPLOYEE
--	WHERE EXTRACT(YEAR FROM HIRE_DATE) = 2000);
	WHERE TO_CHAR(HIRE_DATE, 'YYYY') = 2000);

-- 3. 77년생 여자 사원과 동일한 부서이면서 동일한 사수를 가지고 있는 사원을 조회하시오
--    사번, 이름, 부서코드, 사수번호, 주민번호, 고용일     
SELECT EMP_ID, EMP_NAME, DEPT_CODE, MANAGER_ID, EMP_NO, HIRE_DATE
FROM EMPLOYEE
WHERE (DEPT_CODE, MANAGER_ID) = (
	SELECT DEPT_CODE, MANAGER_ID
	FROM EMPLOYEE 
	WHERE SUBSTR(EMP_NO, 1, 2) = '77'
	AND SUBSTR(EMP_NO, 8, 1) = '2');

----------------------------------------------------------------------

-- 4. 다중행 다중열 서브쿼리
--    서브쿼리 조회 결과 행 수와 열 수가 여러개 일 때

-- 본인 직급의 평균 급여를 받고 있는 직원의
-- 사번, 이름, 직급, 급여를 조회하세요
-- 단, 급여와 급여 평균은 만원단위로 계산하세요 TRUNC(컬럼명, -4)    

-- 1) 급여를 200, 600만 받는 직원 (200만, 600만이 평균급여라 생각 할 경우)
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE 
WHERE SALARY IN(2000000, 6000000);

-- 2) 직급별 평균 급여
SELECT JOB_CODE, TRUNC(AVG(SALARY), -4)
FROM EMPLOYEE 
GROUP BY JOB_CODE;

-- 3) 본인 직급의 평균 급여를 받고 있는 직원
SELECT EMP_ID, EMP_NAME, JOB_CODE, SALARY
FROM EMPLOYEE 
WHERE (JOB_CODE, TRUNC(SALARY, -4)) IN (
	SELECT JOB_CODE, TRUNC(AVG(SALARY), -4)
	FROM EMPLOYEE 
	GROUP BY JOB_CODE);

-------------------------------------------------------------------------------

-- 5. 상관(상호연관) 서브쿼리
--   상관쿼리는 메인쿼리가 사용하는 테이블값을 서브쿼리가 이용해서 결과를 만듦
--   메인쿼리의 테이블값이 변경되면 서브쿼리의 결과값도 바뀌게 되는 구조임
--   먼저 메인쿼리 한 행을 조회하고 해당 행이 서브쿼리의 조건을 충족하는지 확인하여 SELECT를 진행함

-- 사수가 있는 직원의 사번, 이름, 부서명, 사수사번 조회
-- EXISTS : 서브쿼리에서 조회된 결과가 일치하는 행이 메인쿼리에 하나라도 있으면 조회 결과에 포함
--          (서브쿼리 조회 결과가 1행 이상이면 메인쿼리 행을 결과에 포함)
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, MANAGER_ID
FROM EMPLOYEE MAIN
LEFT JOIN DEPARTMENT ON(DEPT_ID = DEPT_CODE)
WHERE EXISTS (
	SELECT EMP_ID FROM EMPLOYEE SUB
	WHERE SUB.EMP_ID = MAIN.MANAGER_ID);

-- 직급별 급여 평균보다 급여를 많이 받는 직원의 이름, 직급코드, 급여 조회
SELECT EMP_NAME, JOB_CODE, SALARY 
FROM EMPLOYEE MAIN
WHERE SALARY > (
	SELECT AVG(SALARY)
	FROM EMPLOYEE SUB
	WHERE SUB.JOB_CODE = MAIN.JOB_CODE);

-- 부서별 입사일이 가장 빠른 사원의 사번, 이름, 부서명(NULL이면 '소속없음'), 직급명, 입사일을 조회하고
-- 입사일이 빠른 순으로 조회하세요 (단, 퇴사한 직원은 제외하고 조회하세요)
-- #1. 어렵지만 신뢰도가 높은 결과
-- IS NULL / IS NOT NULL을 제외한 모든 연산에서 NULL값을 가지는 필드는 제외된다.
SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '소속없음') 부서명, JOB_NAME, HIRE_DATE 
FROM EMPLOYEE MAIN
JOIN JOB USING(JOB_CODE)
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE HIRE_DATE = (
	SELECT MIN(HIRE_DATE)
	FROM EMPLOYEE SUB
	WHERE ENT_YN = 'N'
	AND (MAIN.DEPT_CODE = SUB.DEPT_CODE				-- 부서코드가 같거나 (DEPT_CODE = NULL이면 무조건 FALSE)
	OR (MAIN.DEPT_CODE IS NULL AND SUB.DEPT_CODE IS NULL)))   -- 둘 다 NULL
ORDER BY HIRE_DATE;

-- #2. 쉽지만 신뢰도가 낮은 결과
--     입사일이 같은 사람이 존재할 경우 부정확한 결과를 도출할 수 있음
SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '소속없음') 부서명, JOB_NAME, HIRE_DATE 
FROM EMPLOYEE MAIN
LEFT JOIN JOB USING(JOB_CODE)
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE HIRE_DATE IN (
	SELECT MIN(HIRE_DATE)
	FROM EMPLOYEE SUB
	WHERE ENT_YN = 'N'
	GROUP BY DEPT_CODE)
ORDER BY HIRE_DATE;

-- #3. NVL로 NULL값 처리
SELECT EMP_ID, EMP_NAME, NVL(DEPT_TITLE, '소속없음') 부서명, JOB_NAME, HIRE_DATE 
FROM EMPLOYEE MAIN
JOIN JOB USING(JOB_CODE)
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE HIRE_DATE = (
	SELECT MIN(HIRE_DATE)
	FROM EMPLOYEE SUB
	WHERE ENT_YN = 'N'
	AND NVL(MAIN.DEPT_CODE, '1') = NVL(SUB.DEPT_CODE, '1'))
ORDER BY HIRE_DATE;

----------------------------------------------------------------------------------

-- 6. 스칼라 서브쿼리
--    SELECT절에 사용되는 단일행 서브쿼리 결과(1행만 반환)
--    SQL에서 단일 값을 가르켜 '스칼라'라고 함
--    상관쿼리와 자주 같이 사용됨

-- 각 직원들이 속한 직급의 급여 평균 조회
SELECT EMP_NAME, JOB_CODE, SALARY, (
	SELECT FLOOR(AVG(SALARY))
	FROM EMPLOYEE S
	WHERE S.JOB_CODE = M.JOB_CODE) "급여 평균"
FROM EMPLOYEE M;

-- 모든 사원의 사번, 이름, 관리자사번, 관리자명을 조회
-- 단 관리자가 없는 경우 '없음'으로 표시
-- #1. 스칼라 + 상관 쿼리
SELECT EMP_ID, EMP_NAME, MANAGER_ID, 
	NVL((
		SELECT EMP_NAME
		FROM EMPLOYEE S
		WHERE M.MANAGER_ID = S.EMP_ID
	), '없음') 관리자명
FROM EMPLOYEE M;

-- #2. 셀프조인
SELECT M.EMP_ID, M.EMP_NAME, M.MANAGER_ID, NVL(S.EMP_NAME, '없음') 관리자명
FROM EMPLOYEE M
LEFT JOIN EMPLOYEE S ON(M.MANAGER_ID = S.EMP_ID)
ORDER BY M.EMP_ID;

-----------------------------------------------------------------------

-- 7. 인라인 뷰(INLINE-VIEW)
--    FROM 절에서 서브쿼리를 사용하는 경우로
--    서브쿼리가 만든 결과의 집합(RESULT SET)을 테이블 대신에 사용한다.

-- INLINE : SQL
-- VIEW : 조회 목적의 가상 테이블(원하는 컬럼만 작성)

-- 인라인뷰를 활용한 TOP-N분석
-- 전 직원 중 급여가 높은 상위 5명의
-- 순위, 이름, 급여 조회

-- 1) 전 직원을 급여가 높은 순으로 조회
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
ORDER BY SALARY DESC;

-- 2) ROWNUM : 조회 결과에 행 번호를 추가하는 가상의 컬럼
SELECT EMP_NAME, ROWNUM
FROM EMPLOYEE;

-- 3) ROWNUM을 WHERE절에 사용하기
SELECT EMP_NAME, ROWNUM
FROM EMPLOYEE
WHERE ROWNUM <= 5;

-- * 조건절에 ROWNUM 사용 시 주의사항
-- 조건절에 ROWNUM을 작성하는 경우 조건 범위 내에 1번행이 반드시 포함되어야 한다.
SELECT EMP_NAME, ROWNUM
FROM EMPLOYEE
WHERE ROWNUM = 5;
--> 조회 결과 없음 (조회 결과에 ROWNUM이 붙는거라 1개뿐인 결과에 ROWNUM=5가 붙을 수 없음)
SELECT EMP_NAME, ROWNUM
FROM EMPLOYEE
WHERE ROWNUM = 5 OR ROWNUM = 1;
--> 1행만 조회됨

-- 4) 1~3번을 토대로 급여 상위 5명 조회 시도
SELECT EMP_NAME, SALARY, ROWNUM
FROM EMPLOYEE 
WHERE ROWNUM <= 5
ORDER BY SALARY DESC;
--> EMPLOYEE 테이블 상단에 위치한 5명을 조회하여 그 5명 안에서 정렬
--> 문제원인 : SELECT 해석 순서 상 ORDER BY가 가장 마지막으로 해석되기 때문에 
--           급여 상위 5명이라는 의도와 맞지 않는 형태로 조회됨
--> 해결방법 : 인라인뷰(FROM절에 작성되는 서브쿼리)를 이용하여 해결

-- 해결 1) 급여 내림차순 조회
SELECT EMP_NAME, SALARY
FROM EMPLOYEE
ORDER BY SALARY DESC;
--> 해결 1의 조회 결과(RESULT SET)를 가상의 테이블인 뷰(VIEW)로 이용(행 순서, 컬럼 그대로 이용)

-- 해결 2) 해결 1의 조회 결과를 FROM절에 작성하여 상위 5해만 조회
SELECT ROWNUM, EMP_NAME, SALARY
FROM (
	SELECT EMP_NAME, SALARY
	FROM EMPLOYEE
	ORDER BY SALARY DESC) -- 메인쿼리에 포함된 VIEW 생성 구문 => 인라인 뷰
WHERE ROWNUM <= 5;

-- 급여 평균이 3위 안에 드는 부서의 부서코드와 부서명, 평균급여를 조회
SELECT DEPT_CODE, DEPT_TITLE, 평균급여
FROM (
	SELECT DEPT_CODE, FLOOR(AVG(SALARY)) 평균급여
	FROM EMPLOYEE
	GROUP BY DEPT_CODE
	ORDER BY 평균급여 DESC) -- 메인쿼리에 포함된 VIEW 생성 구문 => 인라인 뷰
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE ROWNUM <= 3;

-- * 인라인 뷰 사용 시 컬럼명에 유의해야 한다.
-- 메인 쿼리에서 인라인 뷰의 컬럼명을 그대로 사용해야 한다.
-- EX. 급여 평균이 590만인 부서를 조회
SELECT DEPT_CODE, DEPT_TITLE, 평균급여
FROM (
	SELECT DEPT_CODE, FLOOR(AVG(SALARY)) 평균급여
	FROM EMPLOYEE
	GROUP BY DEPT_CODE
	ORDER BY 평균급여 DESC) -- 메인쿼리에 포함된 VIEW 생성 구문 => 인라인 뷰
JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
WHERE 평균급여 = 5900000;

------------------------------------------------------------------------

-- 8. WITH
--    서브쿼리에 이름을 붙여주고 사용시 이름을 사용하게 함
--    인라인뷰로 사용될 서브쿼리에 주로 사용됨
--    ***실행 속도도 빨라진다***는 장점이 있다. (SQL 실행 시 WITH절을 가장 먼저 수행)

-- 전 직원의 급여 순위 
-- 순위, 이름, 급여 조회
SELECT ROWNUM, EMP_NAME, SALARY
FROM (
	SELECT EMP_NAME, SALARY
	FROM EMPLOYEE 
	ORDER BY SALARY DESC);

--  WITH를 이용해서 MAIN 쿼리를 깔끔하게 정리
WITH TOP_SALARY AS (
	SELECT EMP_NAME, SALARY
	FROM EMPLOYEE 
	ORDER BY SALARY DESC)
SELECT ROWNUM, EMP_NAME, SALARY 
FROM TOP_SALARY;

--------------------------------------------------------------------------

-- 9. RANK() OVER / DENSE_RANK() OVER
-- SELECT 절에만 사용 가능
-- RANK() OVER : 동일한 순위 이후의 등수를 동일한 인원 수 만큼 건너뛰고 순위 계산
--               EX) 공동 1위가 2명이면 다음 순위는 2위가 아니라 3위

-- 급여를 많이 받는 순서로 조회
SELECT EMP_NAME, SALARY, RANK() OVER(ORDER BY SALARY DESC) 순위
FROM EMPLOYEE;

-- 급여 상위 5명 조회(1)
SELECT EMP_NAME, SALARY, RANK() OVER(ORDER BY SALARY DESC) 순위
FROM EMPLOYEE
WHERE RANK() OVER(ORDER BY SALARY DESC) <= 5;
--> ORA-30483: 윈도우 함수를 여기에 사용할 수 없습니다

-- 급여 상위 5명 조회(2)
SELECT * FROM (
	SELECT EMP_NAME, SALARY, RANK() OVER(ORDER BY SALARY DESC) 순위
	FROM EMPLOYEE)
WHERE ROWNUM <= 5;

-- DENSE_RANK() OVER : 동일한 순위 이후의 등수를 이후의 순위로 계산
--                     EX) 공동 1위가 2명이어도 다음 순위는 2위
SELECT EMP_NAME, SALARY, DENSE_RANK() OVER(ORDER BY SALARY DESC) 순위
FROM EMPLOYEE;
