CREATE TABLE LETTERS
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    number     VARCHAR(20),
    letterDate DATE DEFAULT NULL,
    theme 		VARCHAR(250),
    published  bool,
    file blob,
    fileType varchar(10),
    note varchar(1000)
    
);