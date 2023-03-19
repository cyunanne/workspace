/*
 * DCL(Data Control Language)
 * : 사용자(USER)의 DB 또는 DB객체에 대한 접근 권한을 부여(GRANT)하고 회수(REVOKE)하는 언어
 * 
 * <사용자(계정) 구분>
 * 1. 관리자 계정
 *   - DB의 생성과 관리를 담당하는 계정
 *   - 모든 권한과 책임을 가짐
 *   - ex. SYS(최고관리자), SYSTEM(SYS에서 권한 몇 개 제외)
 * 2. 사용자 계정
 *   - 데이터베이스의 질의, 갱신, 보고서 작성 등의 작업을 수행할 수 있는 계정
 *   - 원칙상 업무에 필요한 최소한의 권한만 갖는다.
 *   - ex. kh계정, workbook계정
 * 
 * <권한의 종류>
 * 1. 시스템 권한 : DB접속, 객체 생성 권한
 *   -> 관리자 계정 또는 관리자 권한이 있는 계정
 *
 *   CRETAE SESSION   : 데이터베이스 접속 권한
 *   CREATE TABLE     : 테이블 생성 권한
 *   CREATE VIEW      : 뷰 생성 권한
 *   CREATE SEQUENCE  : 시퀀스 생성 권한
 *   CREATE PROCEDURE : 함수(프로시져) 생성 권한
 *   CREATE USER      : 사용자(계정) 생성 권한
 *   DROP USER        : 사용자(계정) 삭제 권한
 *   DROP ANY TABLE   : 임의 테이블 삭제 권한
 *
 * 2. 객체 권한 : 특정 객체를 조작할 수 있는 권한
 *   cf. 객체 : TABLE, VIEW, INDEX, SEQUENC, USER 등
 * 
 *     권한 종류                 설정 객체
 *     SELECT              TABLE, VIEW, SEQUENCE
 *     INSERT              TABLE, VIEW
 *     UPDATE              TABLE, VIEW
 *     DELETE              TABLE, VIEW
 *     ALTER               TABLE, SEQUENCE
 *     REFERENCES          TABLE
 *     INDEX               TABLE
 *     EXECUTE             PROCEDURE
 * 
 */

---------------------- (관리자 계정으로 수행) --------------------------------------------------
-- 사용자 계정 sample 생성

-- 0. SQL 구문 작성 형식은 일반 형식(예전 12c버전 이전 형식)으로 변경
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 1. 사용자 계정 생성
-- CREATE USER 계정명 IDENTIFIED BY 비밀번호;
--CREATE USER CYN_SAMPLE IDENTIFIED BY sample1234; -- 대소문자 구분 안함
CREATE USER CYN_SAMPLE IDENTIFIED BY "sample1234"; -- 대소문자 구분됨

-- 2. 생성된 샘플 계정의 접속 방법 추가
-- 샘플 계정으로 접속하려고 했더니 오류 발생
-- ORA-01045: 사용자 CYN_SAMPLE는 CREATE SESSION 권한을 가지고있지 않음; 로그온이 거절되었습니다
--> 계정에 접속 권한 부여해야 함

-- 3. 접속 권한 부여
-- GRANT 권한[, 권한,...] TO 계정명;
GRANT CREATE SESSION TO CYN_SAMPLE;

-- 4. 권한 부여 후 접속방법 추가
--> 성공!

---------------------- (CYN_SAMPLE 계정으로 수행) --------------------------------------------------
-- 5. 테이블 생성
CREATE TABLE TB_MEMBER(
	MEMBER_NO NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR2(30) NOT NULL,
	MEMBER_PW VARCHAR2(30) NOT NULL
);
-- ORA-01031: 권한이 불충분합니다
--> 다시 관리자 계정으로 전환해서 테이블 생성 관련 권한을 부여

---------------------- (관리자 계정으로 수행) --------------------------------------------------
-- 6. 테이블 생성 관련 권한 부여 + 객체 생성 공간 할당
GRANT CREATE TABLE TO CYN_SAMPLE;

ALTER USER CYN_SAMPLE DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM; -- 공간 무제한

---------------------- (CYN_SAMPLE 계정으로 수행) --------------------------------------------------
-- 7. 다시 테이블 생성 시도
CREATE TABLE TB_MEMBER(
	MEMBER_NO NUMBER PRIMARY KEY,
	MEMBER_ID VARCHAR2(30) NOT NULL,
	MEMBER_PW VARCHAR2(30) NOT NULL
);

/* 객체권한 부여/회수 */
---------------------- (CYN_SAMPLE 계정으로 수행) --------------------------------------------------
-- 1. KH 계정의 EMPLOYEE 테이블 조회
SELECT * FROM KH_CYN.EMPLOYEE;
-- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다
--> 권한이 없어서 다른 계정의 테이블이 보이지 않음

---------------------- (KH 계정으로 수행) --------------------------------------------------
-- 2. sample 계정에 employee 테이블 select 권한 부여
-- 시스템 권한 부여 : GRANT 권한[, 권한,...]         TO 계정명;
-- 객체 권한 부여  : GRANT 권한[, 권한,...] ON 객체명 TO 계정명;
GRANT SELECT ON EMPLOYEE TO CYN_SAMPLE;

---------------------- (CYN_SAMPLE 계정으로 수행) --------------------------------------------------
-- 3. KH 계정의 EMPLOYEE 테이블 조회 재시도
SELECT * FROM KH_CYN.EMPLOYEE;
--> 테이블 조회 성공!

-- 4. 선동일 삭제
DELETE FROM KH_CYN.EMPLOYEE WHERE EMP_ID = 200;
--> ORA-01031: 권한이 불충분합니다

---------------------- (KH 계정으로 수행) --------------------------------------------------
-- 5. SAMPLE 계정의 employee 테이블 조회 권한 회수
-- 시스템 권한 회수 : REVOKE 권한[, 권한,...]         FROM 계정명;
-- 객체 권한 회수  : REVOKE 권한[, 권한,...] ON 객체명 FROM 계정명;
REVOKE SELECT ON EMPLOYEE FROM CYN_SAMPLE;

---------------------- (CYN_SAMPLE 계정으로 수행) --------------------------------------------------
-- 6. KH 계정의 EMPLOYEE 테이블 조회 재시도
SELECT * FROM KH_CYN.EMPLOYEE;
--> ORA-00942: 테이블 또는 뷰가 존재하지 않습니다 (= 조회 권한 없음)

---------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------

/*
 * ROLE (역할 = 권한의 묶음)
 * 1. 여러 권한을 하나로 묶어둠
 * 2. 어려운 권한명(SESSION 등)을 쉽게 변경
 */

---------------------- (관리자 계정으로 수행) --------------------------------------------------
-- 1) RESOURCE : db사용을 위한 기본 객체 생성 권한을 묶어둔 것
SELECT * FROM ROLE_SYS_PRIVS WHERE ROLE='RESOURCE'; -- 8행 == 권한 8개

--> sample 계정에 시퀀스, 프로시저, 클러스터, 인덱스타입, 연산자, 타입, 트리거, 테이블 생성 권한 부여
-- 모든 권한을 주기 위해 아래와 같은 SQL 작성이 필요하게 된다....
-- GRANT CREATE SEQUENCE, CREATE PROCEDURE, CREATE CLUSTER, CREATE INDEXTYPE, 
-- CREATE OPERATOR, CREATE TYPE, CREATE TRIGGER, CREATE TABLE TO CYN_SAMPLE;
--> 이것을 간단하게 하기 위해 ROLE 활용
GRANT RESOURCE TO CYN_SAMPLE;
REVOKE RESOURCE FROM CYN_SAMPLE;

-- 사용자에게 부여된 시스템권한 조회
SELECT * FROM DBA_SYS_PRIVS WHERE GRANTEE = 'CYN_SAMPLE';

-- 사용자에게 부여된 롤 조회
SELECT * FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'CYN_SAMPLE';


-- 2) CONNECT 
-- CREATE SESSION(DB 접속)권한 하나만 가지고 있는 ROLE
SELECT * FROM ROLE_SYS_PRIVS WHERE ROLE = 'CONNECT';

-- 앞으로 사용자 계정을 생성할 경우 권한 부여 구문 (일반적으로 부여하는 권한)
GRANT CONNECT, RESOURCE, CREATE VIEW TO CYN_SAMPLE;


REVOKE CREATE SESSION FROM CYN_SAMPLE;