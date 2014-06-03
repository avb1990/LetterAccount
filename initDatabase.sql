CREATE TABLE LETTERS
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    number     VARCHAR(20),
    letterDate DATE DEFAULT NULL,
    theme 		VARCHAR(250),
    published  bool,    
    note varchar(1000)
    
);

create table letterFile(
	id INT PRIMARY KEY AUTO_INCREMENT,
	file mediumblob,
    fileType varchar(10),
    CONSTRAINT `FKLFLS` FOREIGN KEY (`id`) REFERENCES `letters` (`id`)
);