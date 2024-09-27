-- 1. 게임 테이블 삭제

-- 1-1. 이연홍 테이블 삭제
DROP TABLE IF EXISTS game_list CASCADE;

-- 1-2. 홍진기 테이블 삭제
DROP TABLE IF EXISTS account_list CASCADE;
DROP TABLE IF EXISTS account_authority CASCADE;
DROP TABLE IF EXISTS comment_list CASCADE;

-- 1-3. 정소율 테이블 삭제
DROP TABLE IF EXISTS game_order CASCADE;
DROP TABLE IF EXISTS game_payment CASCADE;
DROP TABLE IF EXISTS game_cart CASCADE;

-- 2. 게임 테이블 생성
-- 2-1. 이연홍 테이블 생성 
CREATE TABLE IF NOT EXISTS game_list
(
	-- 칼럼 레벨 정의 
	gameCode INT AUTO_INCREMENT COMMENT '게임코드',
    gameName VARCHAR(100) NOT NULL COMMENT '게임이름',
	gameStorage VARCHAR(100) NOT NULL COMMENT'게임용량',
    gamePrice INT NOT NULL COMMENT '게임가격',
    uploadDate TIME NOT NULL COMMENT '업로드일자',
    gameOrigin VARCHAR(100) NOT NULL COMMENT '게임출처',
    gameRequirement VARCHAR(100) NOT NULL COMMENT '게임사양',
    gamePicture TEXT NOT NULL COMMENT '원본사진',
    gameThumbnail TEXT NOT NULL COMMENT '썸네일사진',
	
    -- 테이블 레벨 정의
    CONSTRAINT pk_gameCode PRIMARY KEY (gameCode)
)ENGINE=INNODB COMMENT "게임목록";

-- 2-2. 홍진기 테이블 생성
CREATE TABLE IF NOT EXISTS account_authority (
	authorityCode		INT	        AUTO_INCREMENT COMMENT '권한코드',
	authorityCodeName	VARCHAR(20)	NOT NULL COMMENT '권한이름',
    
    CONSTRAINT pk_authorityCode PRIMARY KEY (authorityCode)
)ENGINE=INNODB COMMENT "권한";


CREATE TABLE IF NOT EXISTS account_list (
	userCode			INT			 AUTO_INCREMENT			COMMENT '회원코드',
	userId				VARCHAR(20)	 NOT NULL				COMMENT '회원아이디',
	userName			VARCHAR(20)	 NOT NULL				COMMENT '회원이름',
	nickName			VARCHAR(20)	 NOT NULL				COMMENT '회원별명',
	pwd					VARCHAR(255) NOT NULL				COMMENT '회원비밀번호',
	birthday			Date		 NOT NULL				COMMENT '회원생일',
	email				VARCHAR(100) NOT NULL				COMMENT '회원이메일',
	phone				VARCHAR(11)	 NOT NULL				COMMENT '회원전화번호',
	suspension			VARCHAR(1)	 NOT NULL	DEFAULT 'N'	COMMENT '정지여부',
	deletion			VARCHAR(1)	 NOT NULL	DEFAULT 'N'	COMMENT '탈퇴여부',
	profileimg			text		 NOT NULL				COMMENT '프로필사진',
	authorityCode		INT			 NOT NULL				COMMENT '권한코드',
    
	CONSTRAINT pk_userCode PRIMARY KEY (userCode),
	CONSTRAINT fk_authorityCode FOREIGN KEY (authorityCode) REFERENCES account_authority (authorityCode)
)ENGINE=INNODB COMMENT "회원목록";

CREATE TABLE IF NOT EXISTS comment_list (
	reviewCode			INT			AUTO_INCREMENT	COMMENT '댓글코드',
	content				text		NOT NULL	COMMENT '내용',
	star				INT			NOT NULL	COMMENT '별점',
	createDate			timestamp	NOT NULL	COMMENT '생성일',
	updateDate			timestamp	NOT NULL	COMMENT '수정일',
	gameCode			INT			NOT NULL	COMMENT '게임코드',
	userCode			INT			NOT NULL	COMMENT '회원코드',
	isCommentofComment  VARCHAR(1)	NOT NULL	COMMENT	'대댓글',
	reviewCode2			INT			NOT NULL 	COMMENT '부모댓글',
    
    CONSTRAINT pk_reviewCode PRIMARY KEY (reviewCode),
    CONSTRAINT fk_comment_gameCode FOREIGN KEY (gameCode) REFERENCES game_list (gameCode),
    CONSTRAINT fk_comment_userCode FOREIGN KEY (userCode) REFERENCES account_list (userCode),
    CONSTRAINT fk_comment_reviewCode2 FOREIGN KEY (reviewCode2) REFERENCES comment_list (reviewCode)
)ENGINE=INNODB COMMENT "댓글";

-- 2-3. 정소율 테이블 생성
CREATE TABLE IF NOT EXISTS game_cart
(
    -- column level constraints
    cartCode INT AUTO_INCREMENT COMMENT '장바구니코드',
    userCode INT NOT NULL COMMENT '회원코드',
    gameCode INT NOT NULL COMMENT '게임코드',
    
    -- table level constraints
    CONSTRAINT pk_cartCode PRIMARY KEY (cartCode),
    CONSTRAINT fk_userCode FOREIGN KEY (userCode) REFERENCES account_list (userCode),
    CONSTRAINT fk_gameCode FOREIGN KEY (gameCode) REFERENCES game_list (gameCode)
) ENGINE=INNODB COMMENT '장바구니';

CREATE TABLE IF NOT EXISTS game_payment
(
    -- column level constraints
    paymentCode INT AUTO_INCREMENT COMMENT '결제코드',
    paymentDate TIME NOT NULL COMMENT '결제날짜',
    amount INT NOT NULL COMMENT '결제금액',
    userCode INT NOT NULL COMMENT '회원코드',
    
    -- table level constraints
    CONSTRAINT pk_paymentCode PRIMARY KEY (paymentCode),
    CONSTRAINT fk_payment_userCode FOREIGN KEY (userCode) REFERENCES account_list (userCode)
) ENGINE=INNODB COMMENT '결제';

CREATE TABLE IF NOT EXISTS game_order
(
    -- column level constraints
    orderCode INT AUTO_INCREMENT COMMENT '주문코드',
    gameCode INT NOT NULL COMMENT '게임코드',
    cartCode INT NOT NULL COMMENT '장바구니코드',
    userCode INT NOT NULL COMMENT '회원코드',
    paymentCode INT NOT NULL COMMENT '결제코드',
    
    -- table level constraints
    CONSTRAINT pk_orderCode PRIMARY KEY (orderCode),
    CONSTRAINT fk_order_gameCode FOREIGN KEY (gameCode) REFERENCES game_list (gameCode),
    CONSTRAINT fk_order_cartCode FOREIGN KEY (cartCode) REFERENCES game_cart (cartCode),
    CONSTRAINT fk_order_userCode FOREIGN KEY (userCode) REFERENCES account_list (userCode),
    CONSTRAINT fk_paymentCode FOREIGN KEY (paymentCode) REFERENCES game_payment (paymentCode)
) ENGINE=INNODB COMMENT '주문';


-- 3. 데이터 삽입
-- 3-1. 이연홍 데이터 삽입
INSERT INTO game_list VALUES (null, 'Super Mario Bros.', '32KB', 5000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/supermariobros', 'c://img/thumbnail/supermariobros');
INSERT INTO game_list VALUES (null, 'The Legend of Zelda', '128KB', 7000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/thelegendofzelda', 'c://img/thumbnail/thelegendofzelda');
INSERT INTO game_list VALUES (null, 'Pac-Man', '16KB', 2000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/pacman', 'c://img/thumbnail/pacman');
INSERT INTO game_list VALUES (null, 'Sonic the Hedgehog', '1MB', 9000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/sonicthehedgehog', 'c://img/thumbnail/sonicthehedgehog');
INSERT INTO game_list VALUES (null, 'Tetris', '32KB', 3000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/tetris', 'c://img/thumbnail/tetris');
INSERT INTO game_list VALUES (null, 'Street Fighter 2', '3MB', 12000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/streetfighter2', 'c://img/thumbnail/streetfighter2');
INSERT INTO game_list VALUES (null, 'Donkey Kong', '20KB', 1000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/dongkeykong', 'c://img/thumbnail/dongkeykong');
INSERT INTO game_list VALUES (null, 'Mega Man 2', '512KB', 10000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/megaman2', 'c://img/thumbnail/megaman2');
INSERT INTO game_list VALUES (null, 'Final Fantasy 6', '5MB', 15000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/finalfantasy6', 'c://img/thumbnail/finalfantasy6');
INSERT INTO game_list VALUES (null, 'Castlevania', '128KB', 6000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/castlevania', 'c://img/thumbnail/castlevania');
INSERT INTO game_list VALUES (null, 'Metroid', '128KB', 6000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/metroid', 'c://img/thumbnail/metroid');
INSERT INTO game_list VALUES (null, 'Contra', '128KB', 6000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/contra', 'c://img/thumbnail/contra');
INSERT INTO game_list VALUES (null, 'Chrono Trigger', '4MB', 15000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/chronotrigger', 'c://img/thumbnail/chronotrigger');
INSERT INTO game_list VALUES (null, 'Mortal Kombat', '2MB', 12000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/mortalkombat', 'c://img/thumbnail/mortalkombat');
INSERT INTO game_list VALUES (null, 'Duck Hunt', '32KB', 5000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/duckhunt', 'c://img/thumbnail/duck/hunt');
INSERT INTO game_list VALUES (null, 'The Oregon Trail', '64KB', 7000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/theorigontrail', 'c://img/thumbnail/theoregontrail');
INSERT INTO game_list VALUES (null, 'EarthBound', '3MB', 15000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/earthbound', 'c://img/thumbnail/earthbound');
INSERT INTO game_list VALUES (null, 'Double Dragon', '256KB', 6000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/doubledragon', 'c://img/thumbnail/doubledragon');
INSERT INTO game_list VALUES (null, 'Galaga', '32KB', 3000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/galaga', 'c://img/thumbnail/galaga');
INSERT INTO game_list VALUES (null, 'F-Zero', '1.5MB', 13000, SYSDATE(), 'Beta Studio', 'Processor: i3 or above, Memory: 4GB or above', 'c://img/original/fzero', 'c://img/thumbnail/fzero');

-- 3-2. 홍진기 데이터 삽입
-- 3-2-1. 홍진기 권한 데이터 삽입 
INSERT INTO account_authority VALUES (null, 'regular');
INSERT INTO account_authority VALUES (null, 'admin');

-- 3-2-2. 홍진기 계정 데이터 삽입 
INSERT INTO account_list VALUES (null, 'reg1', 'user01', 10001, 'pwd1234', SYSDATE(), 'alpha@gmail.com', '01012345678', '1', 'Y', 'c://img/original/reg1', 1);
INSERT INTO account_list VALUES (null, 'adm1', 'user02', 10002, 'pwd2345', SYSDATE(), 'beta@gamil.com', '01012345678', '1', 'Y', 'c://img/original/adm1', 2);

-- 3-2-3. 홍진기 댓글 데이터 삽입
 INSERT INTO comment_list VALUES (null, 'lalalalalala', '5', SYSDATE(), SYSDATE(), 10, 1, 0);
 INSERT INTO comment_list VALUES (null, 'JFKDLAJFEA', '5', SYSDATE(), SYSDATE(), 3, 2, 1);
 INSERT INTO comment_list VALUES (null, 'WJKFEJALFEJI', '5', SYSDATE(), SYSDATE(), 20, 1, 2);
 INSERT INTO comment_list VALUES (null, 'WUALALALFJEKLA', '5', SYSDATE(), SYSDATE(), 18, 2, 1);

-- 3-3. 정소율 데이터 삽입
-- 3-3-1. game_cart 데이터 삽입 
INSERT INTO game_cart VALUES (null, 1, 1);
INSERT INTO game_cart VALUES (null, 1, 2);
INSERT INTO game_cart VALUES (null, 2, 10);
INSERT INTO game_cart VALUES (null, 2, 11);

-- 3-3-2. 정소율 game_payement 데이터 삽입 
INSERT INTO game_payment VALUES (null, SYSDATE(),
(SELECT
    SUM(gamePrice)
FROM
	game_cart
LEFT JOIN game_list ON game_cart.gameCode=game_list.gameCode
WHERE
	userCode = 1
),
1);

INSERT INTO game_payment VALUES (null, SYSDATE(),
(SELECT
    SUM(gamePrice)
FROM
	game_cart
LEFT JOIN game_list ON game_cart.gameCode=game_list.gameCode
WHERE
	userCode = 2
),
2);

-- 3-3-3. 정소율 game-order 데이터 삽입 
INSERT INTO game_order VALUES (null, 
(SELECT gameCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 0, 1),
(SELECT cartCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 0, 1),
(SELECT gp.userCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 0, 1),
(SELECT paymentCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 0, 1));

INSERT INTO game_order VALUES (null, 
(SELECT gameCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 1, 1),
(SELECT cartCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 1, 1),
(SELECT gp.userCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 1, 1),
(SELECT paymentCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 1, 1));

INSERT INTO game_order VALUES (null, 
(SELECT gameCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 2, 1),
(SELECT cartCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 2, 1),
(SELECT gp.userCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 2, 1),
(SELECT paymentCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 2, 1));

INSERT INTO game_order VALUES (null, 
(SELECT gameCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 3, 1),
(SELECT cartCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 3, 1),
(SELECT gp.userCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 3, 1),
(SELECT paymentCode FROM game_payment AS gp INNER JOIN game_cart AS gc ON gp.userCode=gc.userCode LIMIT 3, 1));



COMMIT;