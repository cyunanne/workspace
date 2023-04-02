-- CTRL + ENTER : 선택한 SQL 수행

-- 계정 설정(뭔진모름)
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 계정 생성
CREATE USER kh_cyn IDENTIFIED BY "oracle_cyn123A";

-- 사용자 계정에 권한 부여
GRANT resource, CONNECT TO kh_cyun;

-- 객체가 생성될 수 있는 공간 할당량 지정
ALTER USER kh_cyn DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM;