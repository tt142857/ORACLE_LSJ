

/* Create Tables */

-- 첨부파일
CREATE TABLE TB_ATCH
(
	-- 첨부파일_ID
	ATCH_ID char(36) NOT NULL,
	-- 첨부파일_순번
	ATCH_SEQ int NOT NULL,
	-- 첨부파일_원본_명
	ATCH_ORIG_NM varchar(256),
	-- 첨부파일_저장_명
	ATCH_SAVE_NM char(36),
	-- 첨부파일_경로
	ATCH_PATH varchar(256),
	-- 첨부파일_사이즈
	ATCH_SIZE bigint,
	-- 첨부파일_확장자
	ATCH_EXTN varchar(10),
	-- 등록_ID
	RGST_ID varchar(20),
	-- 등록_일시
	RGST_DT datetime,
	-- 수정_ID
	UPDT_ID varchar(20),
	-- 수정_일시
	UPDT_DT datetime,
	-- 삭제_여부
	DEL_YN char(1) DEFAULT 'N',
	PRIMARY KEY (ATCH_ID, ATCH_SEQ)
);


-- 게시판
CREATE TABLE TB_BOARD
(
	-- 게시판_번호
	BOARD_NO bigint NOT NULL,
	-- 게시판_구분
	BOARD_DIV varchar(20),
	-- 게시판_제목
	BOARD_TTL varchar(200),
	-- 게시판_분류
	BOARD_CAT varchar(20),
	-- 게시판_내용
	BOARD_CONT varchar(4000),
	-- 게시판_조회수
	BOARD_HIT int,
	-- 첨부파일_ID
	ATCH_ID char(36),
	-- 등록_ID
	RGST_ID varchar(20),
	-- 등록_일시
	RGST_DT datetime,
	-- 수정_ID
	UPDT_ID varchar(20),
	-- 수정_일시
	UPDT_DT datetime,
	-- 삭제_여부
	DEL_YN char(1) DEFAULT 'N',
	PRIMARY KEY (BOARD_NO)
);


-- 댓글
CREATE TABLE TB_CMNT
(
	-- CMNT_SEQ
	CMNT_SEQ int NOT NULL,
	-- 게시판_번호
	BOARD_NO bigint NOT NULL,
	-- 댓글_내용
	CMNT_CONT varchar(4000),
	-- 등록_ID
	RGST_ID varchar(20),
	-- 등록_일시
	RGST_DT datetime,
	-- 수정_ID
	UPDT_ID varchar(20),
	-- 수정_일시
	UPDT_DT datetime,
	-- 삭제_여부
	DEL_YN char(1) DEFAULT 'N',
	PRIMARY KEY (CMNT_SEQ, BOARD_NO)
);


-- 코드
CREATE TABLE TB_CODE
(
	-- 코드_그룹
	CD_GRP varchar(20) NOT NULL,
	-- 코드
	CD varchar(20) NOT NULL,
	-- 코드_명
	CD_NAME varchar(100),
	-- 코드_설명
	CD_DSC varchar(500),
	-- 순서
	ORD int,
	-- 사용_여부
	USE_YN char(1) DEFAULT 'Y',
	-- 속성1
	ATTR1 varchar(20),
	-- 속성2
	ATTR2 varchar(20),
	-- 등록_ID
	RGST_ID varchar(20),
	-- 등록_일시
	RGST_DT datetime,
	-- 수정_ID
	UPDT_ID varchar(20),
	-- 수정_일시
	UPDT_DT datetime,
	-- 삭제_여부
	DEL_YN char(1) DEFAULT 'N',
	PRIMARY KEY (CD_GRP, CD)
);


-- 코드_그룹
CREATE TABLE TB_CODE_GRP
(
	-- 코드_그룹
	CD_GRP varchar(20) NOT NULL UNIQUE,
	-- 코드_그룹_명
	CD_GRP_NM varchar(100),
	-- 순서
	ORD int,
	-- 사용_여부
	USE_YN char(1) DEFAULT 'Y',
	-- 등록_ID
	RGST_ID varchar(20),
	-- 등록_일시
	RGST_DT datetime,
	-- 수정_ID
	UPDT_ID varchar(20),
	-- 수정_일시
	UPDT_DT datetime,
	-- 삭제_여부
	DEL_YN char(1) DEFAULT 'N',
	PRIMARY KEY (CD_GRP)
);


-- 사용자
CREATE TABLE TB_MEM
(
	-- 사용자_아이디
	MEM_ID varchar(16) NOT NULL,
	-- 사용자_비밀번호
	MEM_PSWD varchar(100),
	-- 사용자_명
	MEM_NM varchar(30),
	-- 사용자_이메일
	MEM_EML varchar(100) NOT NULL UNIQUE,
	PRIMARY KEY (MEM_ID)
);


-- 권한
CREATE TABLE TB_ROLE
(
	-- 권한_아이디
	ROLE_ID varchar(4) NOT NULL,
	-- 권한_명
	ROLE_NM varchar(30),
	PRIMARY KEY (ROLE_ID)
);


-- 사용자_권한
CREATE TABLE TB_USER_ROLE
(
	-- 사용자_아이디
	MEM_ID varchar(16) NOT NULL,
	-- 권한_아이디
	ROLE_ID varchar(4) NOT NULL,
	PRIMARY KEY (MEM_ID, ROLE_ID)
);



/* Create Foreign Keys */

ALTER TABLE TB_CMNT
	ADD FOREIGN KEY (BOARD_NO)
	REFERENCES TB_BOARD (BOARD_NO)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE TB_CODE
	ADD FOREIGN KEY (CD_GRP)
	REFERENCES TB_CODE_GRP (CD_GRP)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE TB_USER_ROLE
	ADD FOREIGN KEY (MEM_ID)
	REFERENCES TB_MEM (MEM_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE TB_USER_ROLE
	ADD FOREIGN KEY (ROLE_ID)
	REFERENCES TB_ROLE (ROLE_ID)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



