-- 1. EMPLOYEE 테이블에서 사원명, 입사일-오늘, 오늘-입사일 조회 단, 입사일-오늘의 별칭은 "근무일수1",
-- 오늘-입사일의 별칭은 "근무일수2"로 하고
-- 모두 정수(내림)처리, 양수가 되도록 처리
SELECT EMP_NAME, FLOOR(ABS(HIRE_DATE-SYSDATE)) 근무일수1, FLOOR(SYSDATE-HIRE_DATE) 근무일수2
FROM EMPLOYEE;

-- 2. EMPLOYEE 테이블에서 사번이 홀수인 사원의 사번, 이름, 이메일, 전화번호 조회
SELECT EMP_ID, EMP_NAME, EMAIL, PHONE 
FROM EMPLOYEE 
WHERE MOD(EMP_ID, 2) = 1;

-- 3. EMPLOYEE 테이블에서 근무한지 20년 이상인 사원의 모든 정보 조회
SELECT *
FROM EMPLOYEE 
WHERE (SYSDATE-HIRE_DATE)/365 >= 20;

-- 4. EMPLOYEE 테이블에서 사원명과 주민번호를 조회
-- 단, 주민번호 9번째 자리부터 끝까지는 '*'문자로 채움 예 : 홍길동 771120-1******
SELECT EMP_NAME, SUBSTR(EMP_NO, 1, 8) || '******' 주민등록번호
FROM EMPLOYEE

-- 5. EMPLOYEE 테이블에서 사원명, 직급코드, 연봉(원) 조회
-- 단, 연봉은 보너스가 적용된 1년치 급여 + ₩57,000,000 으로 표시
-- (급여 + (급여 * 보너스) )* 12
SELECT EMP_NAME, JOB_CODE, TO_CHAR((SALARY + NVL(SALARY * BONUS, 0)) * 12, 'L999,999,999') "연봉(원)"
FROM EMPLOYEE;

-- 6. EMPLOYEE 테이블에서 부서코드가 D5, D9인 직원들 중
-- 2004년도에 입사한 사원의 사번 사원명 부서코드 입사일 조회
SELECT EMP_ID, EMP_NAME, DEPT_CODE, HIRE_DATE
FROM EMPLOYEE
WHERE (DEPT_CODE='D5' OR DEPT_CODE='D9') AND EXTRACT(YEAR FROM HIRE_DATE)=2004;

-- 7. EMPLOYEE 테이블에서 사원명, 입사일, 입사한 달의 근무일수 조회
-- 단, 입사한 날도 근무일수에 포함해서 +1 할 것
SELECT EMP_NAME, HIRE_DATE, LAST_DAY(HIRE_DATE)-HIRE_DATE + 1
FROM EMPLOYEE;

-- 8. EMPLOYEE 테이블에서 사원명, 부서코드, 생년월일, 만 나이 조회
-- 단, 생년월일은 주민번호에서 추출해서 ᄋᄋ년 ᄋᄋ월 ᄋᄋ일로 출력되게 함. 
-- 나이는 주민번호에서 추출해서 날짜데이터로 변환한 다음, 계산.
SELECT 
	EMP_NAME, 
	DEPT_CODE,
	TO_CHAR(TO_DATE(SUBSTR(EMP_NO, 1, 6)) , 'YY"년" MM"월" DD"일"') 생년월일, 
	FLOOR((SYSDATE - TO_DATE(SUBSTR(EMP_NO, 1, 6))) / 365) "만 나이"
FROM EMPLOYEE;