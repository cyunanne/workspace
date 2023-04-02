-- VIEW
-- 논리적 가상 테이블
-- 테이블 모양을 하고는 있지만, 실제 값이 저장되어 있는 것은 아니다.
-- RESULT SET을 저장하는 객체

-- VIEW의 사용목적
-- 1. 복잡한 SELECT문을 쉽게 재사용
-- 2. 테이블의 진짜 모습을 감출 수 있어 보안상 유리

-- EMPLOYEE 테이블에서 모든 사원의 사번, 이름, 부서명, 직급명 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)
JOIN JOB USING(JOB_CODE);
--> VIEW 생성

CREATE VIEW V_EMP AS (
	SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
	FROM EMPLOYEE
	LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)
	JOIN JOB USING(JOB_CODE));
--> ORA-01031: 권한이 불충분합니다.
--> SYS계정으로 접속
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;
--> 각자 계정에 CREATE VIEW 권한 부여
GRANT CREATE VIEW TO 사용자계정;
--> 다시 사용자계정으로 접속 후 뷰 생성 구문 수행

-- 생성된 VIEW를 이용해서 조회
SELECT * FROM V_EMP;

-- 뷰 수정
CREATE OR REPLACE VIEW V_EMP AS
	SELECT EMP_ID 사번, EMP_NAME 이름, DEPT_TITLE 부서명, JOB_NAME 직급명
	FROM EMPLOYEE
	LEFT JOIN DEPARTMENT ON(DEPT_CODE=DEPT_ID)
	JOIN JOB USING(JOB_CODE);
--> ORA-00955: 기존의 객체가 이름을 사용하고 있습니다. ...OR REPACE 옵션 없이 실행 시

-- 컬럼명 변경 확인
SELECT * FROM V_EMP;

-- V_EMP에서 직급이 대리인 사원의 정보를 조회
SELECT * FROM V_EMP WHERE 직급명='대리';

---------------------------------------------------------------------------

-- VIEW를 이용한 DML
CREATE TABLE DEPT_COPY2 AS SELECT * FROM DEPARTMENT;
SELECT * FROM DEPT_COPY2;

-- DEPT_COPY2에서 DEPT_ID, LOCATION_ID 컬럼만 조회하는 뷰 생성
CREATE VIEW V_DCOPY2 
AS SELECT DEPT_ID, LOCATION_ID FROM DEPT_COPY2;
SELECT * FROM V_DCOPY2;

-- VIEW를 이용한 INSERT
INSERT INTO V_DCOPY2 VALUES('D0', 'L3');
SELECT * FROM V_DCOPY2;
--> 뷰는 값을 저장하지 않는다고 햇는데 저장된것처럼 보임
------> 뷰에 저장된 것이 아니라 뷰를 생성할 때 사용한 서브쿼리의 테이블(DEPT_COPY2)에 저장됨.

-- 원본 테이블 확인
SELECT * FROM DEPT_COPY2;
--> 원본 테이블에 삽입됨을 확인
--> INSERT 구문에 포함되지 않은 DEPT_TITLE 컬럼은 NULL이 삽입되어있다.****

ROLLBACK;
SELECT * FROM DEPT_COPY2;

-- 복사한 테이블(DEPT_COPY2)의 DEPT_TITLE 컬럼에 NOT NULL 제약조건 추가
ALTER TABLE DEPT_COPY2 MODIFY DEPT_TITLE NOT NULL;
ALTER TABLE DEPT_COPY2 DROP CONSTRAINT SYS_C0031317;
ALTER TABLE DEPT_COPY2 MODIFY DEPT_TITLE CONSTRAINT NN_DCOPY2_TITLE NOT NULL;

-- VIEW를 이용한 INSERT 재시도
INSERT INTO V_DCOPY2 VALUES('D0', 'L3'); -- 원본 테이블 DEPT_ID, LOCATION_ID 컬럼에 'D0', 'L3' 삽입
----> DEPT_TITLE 컬럼에는 NULL이 삽입된다.
--    BUT DEPT_TITLE 컬럼에는 NOT NULL 제약조건이 설정되어 NULL이 삽입될 수 없다.
--    ORA-01400: NULL을 ("KH_CYN"."DEPT_COPY2"."DEPT_TITLE") 안에 삽입할 수 없습니다
-- ** 대부분의 컬럼에 NOT NULL 제약조건이 추가되어있다.
--    (데이터 무결성을 위해) ... 데이터 무결성 : 중복 없음, 널 없음 => 신뢰도 높은 데이터
--    낫 널이 설정된 테이블을 이용해서 뷰를 만들면 인서트가거의 불가능함
------> 결론 : 뷰를 가지고 디엠엘을 수행하는 행동을 지양해라

---------------------------------------------------------------------------

-- WITH READ ONLY 옵션 추가

-- 읽기전용X로 view 변경 + dept_title도 포함
CREATE OR REPLACE VIEW V_DCOPY2 
AS SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID FROM DEPT_COPY2;

INSERT INTO V_DCOPY2 VALUES	('D0', '기획팀', 'L3'); -- 오류 없이 삽입됨

ROLLBACK;

-- 읽기전용O로 view 변경 + dept_title도 포함
CREATE OR REPLACE VIEW V_DCOPY2 
AS SELECT DEPT_ID, DEPT_TITLE, LOCATION_ID FROM DEPT_COPY2
WITH READ ONLY;

INSERT INTO V_DCOPY2 VALUES	('D0', '기획팀', 'L3');
-- ORA-42399: 읽기 전용 뷰에서는 DML 작업을 수행할 수 없습니다.

---------------------------------------------------------------------------

/* SEQUENCE */

-- 테이블 생성
CREATE TABLE TB_TEST(
	TEST_NO NUMBER PRIMARY KEY,
	TEST_NAME VARCHAR2(30) NOT NULL
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_TEST_NO
START WITH 100 -- 100번부터 시작
INCREMENT BY 5 -- 5씩 증가
MAXVALUE 150   -- 최대값 150
NOMINVALUE     -- 최솟값 없음
NOCYCLE        -- 반복 X
NOCACHE;       -- 미리 만들어두는 숫자 없음

-- 시퀀스 생성 확인
SELECT SEQ_TEST_NO.CURRVAL FROM DUAL;
-- ORA-08002: 시퀀스 SEQ_TEST_NO.CURRVAL은 이 세션에서는 정의 되어 있지 않습니다

SELECT SEQ_TEST_NO.NEXTVAL FROM DUAL; -- 100

SELECT SEQ_TEST_NO.CURRVAL FROM DUAL; -- 100

SELECT SEQ_TEST_NO.NEXTVAL FROM DUAL; -- 105
SELECT SEQ_TEST_NO.NEXTVAL FROM DUAL; -- 110
SELECT SEQ_TEST_NO.NEXTVAL FROM DUAL; -- 115

SELECT SEQ_TEST_NO.CURRVAL FROM DUAL; -- 115

-- TB_TEST에 값 삽입
INSERT INTO TB_TEST VALUES(SEQ_TEST_NO.NEXTVAL, '홍길동'||SEQ_TEST_NO.CURRVAL);
SELECT * FROM TB_TEST;

-- TB_TEST에 값 삽입 추가수행 * 3
INSERT INTO TB_TEST VALUES(SEQ_TEST_NO.NEXTVAL, '홍길동'||SEQ_TEST_NO.CURRVAL);

-- UPDATE에 시퀀스 사용해보기
UPDATE TB_TEST 
SET TEST_NO = SEQ_TEST_NO.NEXTVAL,
	TEST_NAME = '고길동'||SEQ_TEST_NO.CURRVAL
WHERE TEST_NO = (SELECT MAX(TEST_NO) FROM TB_TEST);

-- 가장 마지막 TEST_NO를 조회하는 SELECT
-- #1.
SELECT MAX(TEST_NO) FROM TB_TEST;

-- #2.
SELECT * FROM (
	SELECT TEST_NO FROM TB_TEST
	ORDER BY TEST_NO DESC)
WHERE ROWNUM = 1;

--> 업데이트 추가실행 * 4
UPDATE TB_TEST 
SET TEST_NO = SEQ_TEST_NO.NEXTVAL,
	TEST_NAME = '고길동'||SEQ_TEST_NO.CURRVAL
WHERE TEST_NO = (SELECT MAX(TEST_NO) FROM TB_TEST);
-- ORA-08004: 시퀀스 SEQ_TEST_NO.NEXTVAL exceeds MAXVALUE은 사례로 될 수 없습니다
-- = 시퀀스 MAXVALUE 초과

------------------------------------

/* 시퀀스 변경(alter) */

-- SEQ_TEST_NO의 증가값을 1, 최대값 155로 수정
ALTER SEQUENCE SEQ_TEST_NO
INCREMENT BY 1
MAXVALUE 155;

--> 업데이트 다시실행
UPDATE TB_TEST 
SET TEST_NO   = SEQ_TEST_NO.NEXTVAL,
	TEST_NAME = '고길동'||SEQ_TEST_NO.CURRVAL
WHERE TEST_NO = (SELECT MAX(TEST_NO) FROM TB_TEST);

SELECT * FROM TB_TEST;

------------------------------------

/* VIEW, SEQUENCE 삭제 */
DROP VIEW V_DCOPY2; -- 뷰 삭제

DROP SEQUENCE SEQ_TEST_NO; -- 시퀀스 삭제
-- 시퀀스 값을 변경하고 싶으면 삭제 후에 다시 생성해야 한다.

------------------------------------

/* INDEX */

-- INDEX 사용ㄴ 검색
SELECT * FROM EMPLOYEE WHERE EMP_NAME != '0'; -- 52ms

-- INDEX 사용ㅇ 검색
-- 사용방법 : where 절에 인덱싱 된 컬럼을 언급
SELECT * FROM EMPLOYEE WHERE emp_id = '0'; -- 18ms
--------> 데이터가 적어서 차이 식별 불가

-- 인덱스 확인용 테이블 생성
CREATE TABLE TB_INDEX_TEST(
	TEST_NO NUMBER PRIMARY KEY, -- UNIQUE INDEX 자동생
	TEST_ID VARCHAR2(20) NOT NULL
);

-- TB_INDEX_TES 테이블에 샘플 데이터 100만개 삽입
-- (PL/SQL 사용)
BEGIN
	FOR I IN 1..1000000 -- I가 1부터 1000000까지 1씩 증가
	LOOP
		INSERT INTO TB_INDEX_TEST VALUES(I, 'TEST'||I);
	END LOOP;
	COMMIT;
END;

-- 데이터 삽입 확인
SELECT * FROM TB_INDEX_TEST;

-- INDEX 사용ㄴ 검색
SELECT * FROM TB_INDEX_TEST WHERE TEST_ID = 'TEST500000'; -- 646ms

-- INDEX 사용ㅇ 검색
-- 사용방법 : where 절에 인덱싱 된 컬럼을 언급
SELECT * FROM TB_INDEX_TEST WHERE TEST_NO = 500000; -- 17ms

-- TEST_ID 컬럼에 INDEX 생성
CREATE INDEX IDX_TEST_ID ON TB_INDEX_TEST(TEST_ID);

-- INDEX 생성 후 조회
SELECT * FROM TB_INDEX_TEST WHERE TEST_ID = 'TEST500000'; -- 21ms

-- 인덱스 삭제
DROP INDEX IDX_TEST_ID;

-- 테이블 삭제
DROP TABLE TB_INDEX_TEST;