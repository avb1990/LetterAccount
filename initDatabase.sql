CREATE TABLE LETTERS
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    number     VARCHAR(20)  character set utf8,
    letterDate DATE DEFAULT NULL,
    theme 	 VARCHAR(250)	character set utf8,
    published  bool,    
    note varchar(1000) character set utf8 
    
);

create table letterFile(
	id INT PRIMARY KEY AUTO_INCREMENT,
	file mediumblob,
    fileType varchar(10) character set utf8,
    CONSTRAINT `FKLFLS` FOREIGN KEY (`id`) REFERENCES `letters` (`id`)
);