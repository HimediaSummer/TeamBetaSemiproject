-- 현재 접속중인 MySQL에 존재하는 데이터베이스 목록 출력 
Show databases;

-- mysql 이라는 이름의 데이터베이스로 이동(접속) 
use mysql;

-- ohgiraffers 라는 계정명과 ohgiraffers 라는 비밀번호를 갖는 유저 생성. 
CREATE USER 'betagamsung'@'%' IDENTIFIED BY 'betagamsung';

-- 방금 위에서 생성한 유저가 들어있는지 확인
select * from user;

-- menudb라는 이름의 데이터베이스를 생성 
CREATE DATABASE gamsungdb_jg;
show databases;

-- 방금 생성한 데이터베스이스의 모든 권한을 ohgiraffers 유저에게 부여한 후 확인. 
GRANT ALL PRIVILEGES ON gamsungdb_jg.* TO 'betagamsung'@'%';
SHOW GRANTS FOR 'betagamsung'@'%';

-- 현재 mysql에서 menudb로 이동 
use gamsungdb_jg;
