사전과제
도서관련 API(등록,수정,검색)

Swagger 기본주소 : http://localhost/swagger-ui/


- Intellij Ultimate
- Spring Boot 2.7.3
- MariaDB 10.8.4
- Swagger 3.0.0
- Junit


DDL 

- 책 테이블

CREATE TABLE `BOOKLIST` (
`BOOK_ID` int(11) NOT NULL AUTO_INCREMENT,
`WRITER` varchar(100) DEFAULT NULL,
`NAME` varchar(100) DEFAULT NULL,
`STATUS` varchar(100) DEFAULT NULL,
PRIMARY KEY (`BOOK_ID`)
) 

- 카테고리테이블

CREATE TABLE `CATEGORYDATA` (
`CATEGORY_ID` int(11) NOT NULL,
`CATEGORY` varchar(100) DEFAULT NULL,
`BOOK_ID` int(11) DEFAULT NULL,
PRIMARY KEY (`CATEGORY_ID`),
KEY `categorydata_FK` (`BOOK_ID`),
CONSTRAINT `categorydata_FK` FOREIGN KEY (`BOOK_ID`) REFERENCES `booklist` (`BOOK_ID`)
) 






--

setter 관련 builder변경


DTO 변경필요 (category참조)


대량등록시 insert batch로 변경(만건씩?)
