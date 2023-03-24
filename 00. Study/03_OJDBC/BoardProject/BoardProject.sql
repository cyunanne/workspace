-- 회원제 게시판 
-- 회원(MEMBER) 테이블
-- 게시판(BOARD) 테이블
-- 댓글(COMMENT) 테이블
DROP TABLE "MEMBER"
DROP TABLE "BOARD"
DROP TABLE "COMMENT"

;

-- 회원(MEMBER) 테이블 생성

CREATE TABLE "MEMBER"(
	MEMBER_NO 		NUMBER CONSTRAINT MEMBER_PK PRIMARY KEY,
	MEMBER_ID 		VARCHAR2(30) NOT NULL,
	MEMBER_PW 		VARCHAR2(30) NOT NULL,
	MEMBER_NM 		VARCHAR2(30) NOT NULL,
	MEMBER_GENDER 	CHAR(1) CONSTRAINT GENDER_CHECK CHECK(MEMBER_GENDER IN('M','F')),
	ENROLL_DT 		DATE DEFAULT SYSDATE NOT NULL,
	UNREGISTER_FL 	CHAR(1) DEFAULT 'N' CONSTRAINT UNREGISTER_CHECK CHECK(UNREGISTER_FL IN('N','Y'))
)

;

-- COMMENT ON COLUMN 테이블명.컬럼명 IS '주석내용';
]
COMMENT ON COLUMN "MEMBER".MEMBER_NO IS '회원번호(시퀀스 SEQ_MEMBER_NO)';
COMMENT ON COLUMN "MEMBER".MEMBER_ID IS '회원 아이디';
COMMENT ON COLUMN "MEMBER".MEMBER_PW IS '회원 비밀번호';
COMMENT ON COLUMN "MEMBER".MEMBER_NM IS '회원 이름';
COMMENT ON COLUMN "MEMBER".MEMBER_GENDER IS '성별(M/F)';
COMMENT ON COLUMN "MEMBER".ENROLL_DT IS '가입일';
COMMENT ON COLUMN "MEMBER".UNREGISTER_FL IS '탈퇴여부(Y/N)';

;

-- 게시판(BOARD) 테이블 생성

CREATE TABLE "BOARD" (
	"BOARD_NO"	NUMBER		NOT NULL,
	"BOARD_TITLE"	VARCHAR2(600)		NOT NULL,
	"BOARD_CONTENT"	VARCHAR2(4000)		NOT NULL,
	"CREATE_DT"	DATE	DEFAULT SYSDATE	NULL,
	"READ_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"DELETE_FL"	CHAR(1)	DEFAULT 'N'	CHECK("DELETE_FL" IN('Y','N')),
	"MEMBER_NO"	NUMBER		NOT NULL,
	CONSTRAINT BOARD_MEMBER_FK FOREIGN KEY("MEMBER_NO") REFERENCES "MEMBER"(MEMBER_NO)
);

COMMENT ON COLUMN "BOARD"."BOARD_NO" IS '게시글 번호';
COMMENT ON COLUMN "BOARD"."BOARD_TITLE" IS '게시글 제목';
COMMENT ON COLUMN "BOARD"."BOARD_CONTENT" IS '게시글 내용';
COMMENT ON COLUMN "BOARD"."CREATE_DT" IS '작성일';
COMMENT ON COLUMN "BOARD"."READ_COUNT" IS '조회수';
COMMENT ON COLUMN "BOARD"."DELETE_FL" IS '삭제여부(Y/N)';
COMMENT ON COLUMN "BOARD"."MEMBER_NO" IS '회원번호(FK)';

ALTER TABLE "BOARD" ADD CONSTRAINT "PK_BOARD" PRIMARY KEY (
	"BOARD_NO"
);

;

-- 댓글(COMMENT) 테이블 생성

CREATE TABLE "COMMENT" (
	"COMMENT_NO"	NUMBER		NOT NULL,
	"COMMENT_CONTENT"	VARCHAR2(2000)		NOT NULL,
	"CREATE_DATE"	DATE	DEFAULT SYSDATE	NULL,
	"DELETE_FL"	CHAR(1)	DEFAULT 'N'	CHECK("DELETE_FL" IN('Y','N')),
	"MEMBER_NO"	NUMBER		NOT NULL CONSTRAINT COMMENT_MEMBER_FK REFERENCES "MEMBER"(MEMBER_NO),
	"BOARD_NO"	NUMBER		NOT NULL CONSTRAINT COMMENT_BOARD_FK REFERENCES "BOARD"("BOARD_NO")
);

COMMENT ON COLUMN "COMMENT"."COMMENT_NO" IS '댓글 번호(SEQ_COMMENT_NO)';
COMMENT ON COLUMN "COMMENT"."COMMENT_CONTENT" IS '댓글 내용';
COMMENT ON COLUMN "COMMENT"."CREATE_DATE" IS '댓글 작성일';
COMMENT ON COLUMN "COMMENT"."DELETE_FL" IS '삭제여부(Y/N)';
COMMENT ON COLUMN "COMMENT"."MEMBER_NO" IS '회원번호(FK)';
COMMENT ON COLUMN "COMMENT"."BOARD_NO" IS '게시글번호(FK)';

ALTER TABLE "COMMENT" ADD CONSTRAINT "PK_COMMENT" PRIMARY KEY (
	"COMMENT_NO"
);

;

-- SEQUENCE 생성

CREATE SEQUENCE SEQ_MEMBER_NO NOCACHE;
CREATE SEQUENCE SEQ_BOARD_NO NOCACHE;
CREATE SEQUENCE SEQ_COMMENT_NO NOCACHE;

-- 회원 샘플 3개 INSERT

INSERT INTO "MEMBER" VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user01', 'pass01', '유저일', 'F', DEFAULT, DEFAULT);
INSERT INTO "MEMBER" VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user02', 'pass02', '유저이', 'M', DEFAULT, DEFAULT);
INSERT INTO "MEMBER" VALUES(SEQ_MEMBER_NO.NEXTVAL, 'user03', 'pass03', '유저삼', 'F', DEFAULT, DEFAULT);

SELECT * FROM "MEMBER";

COMMIT;

-- BOARD 테이블 샘플 데이터 3개 INSERT

INSERT INTO "BOARD" VALUES(SEQ_BOARD_NO.NEXTVAL, '샘플 제목1', '내용1 입니다.\n안녕하세요!', DEFAULT, DEFAULT, DEFAULT, 1);
INSERT INTO "BOARD" VALUES(SEQ_BOARD_NO.NEXTVAL, '샘플 제목2', '내용2 입니다.\n안녕하세요!', DEFAULT, DEFAULT, DEFAULT, 1);
INSERT INTO "BOARD" VALUES(SEQ_BOARD_NO.NEXTVAL, '샘플 제목3', '내용3 입니다.\n안녕하세요!', DEFAULT, DEFAULT, DEFAULT, 2);

SELECT * FROM "BOARD";

COMMIT;

-- 게시글 목록 조회
-- 게시글 번호, 제목, 작성자, 작성일, 조회수
-- 최신 게시글이 위쪽으로 오도록 정렬
SELECT BOARD_NO, BOARD_TITLE, MEMBER_NM, CREATE_DT, READ_COUNT
FROM "BOARD" JOIN "MEMBER" USING(MEMBER_NO)
WHERE BOARD_NO != 0 -- INDEX를 사용하기 위해 추가
ORDER BY BOARD_NO DESC; -- 날짜로 정렬하는 것 보다 INDEX로 정렬하는 게 더 빠름

-- 댓글 샘플 데이터 3개 삽입

INSERT INTO "COMMENT" VALUES(SEQ_COMMENT_NO.NEXTVAL, '댓글 샘플 1번', DEFAULT, DEFAULT, 1, 1);
INSERT INTO "COMMENT" VALUES(SEQ_COMMENT_NO.NEXTVAL, '댓글 샘플 2번', DEFAULT, DEFAULT, 2, 1);
INSERT INTO "COMMENT" VALUES(SEQ_COMMENT_NO.NEXTVAL, '댓글 샘플 3번', DEFAULT, DEFAULT, 3, 2);

SELECT * FROM "COMMENT";

COMMIT;

-- 게시글 목록 조회
-- 게시글 번호, 제목, 작성자, 작성일, 조회수
-- 최신 게시글이 위쪽으로 오도록 정렬
SELECT BOARD_NO, BOARD_TITLE, MEMBER_NM, CREATE_DT, READ_COUNT,
	(SELECT COUNT(*) FROM "COMMENT" C WHERE B.BOARD_NO = C.BOARD_NO AND DELETE_FL = 'N') COMMENT_COUNT
FROM "BOARD" B JOIN "MEMBER" USING(MEMBER_NO)
WHERE BOARD_NO != 0 AND DELETE_FL = 'N'-- INDEX를 사용하기 위해 추가
ORDER BY BOARD_NO DESC; -- 날짜로 정렬하는 것 보다 INDEX로 정렬하는 게 더 빠름
