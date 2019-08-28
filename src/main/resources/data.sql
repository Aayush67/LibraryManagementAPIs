

-- INSERT INTO `user_book_mapping` (`user_id`, `book_id`) VALUES (5,6);
INSERT INTO `book` (`id`, `author`,`issue_date`, `name`,`publication`, `published_year`,`return_date`, `status`)
VALUES (1,'Horowitz',null,'DSA','Penguin', 1991, null , 'AVAILABLE'),
       (2,'Openheim',null,'Signals and Communication','Balsam', 1995, null , 'AVAILABLE'),
       (3,'Balagurusamy',null,'Introduction to C','Rupa', 1992, null , 'AVAILABLE'),
       (4,'K Shalivaan',null,'Digital Logic and Design','Vicinable', 1996, null , 'AVAILABLE'),
       (5,'Gaur and Kaul',null,'Math I','Rupa', 1997, null , 'AVAILABLE'),
       (6,'Gaur and Kaul',null,'Math II','Rupa', 1998, null , 'AVAILABLE'),
       (7,'H C Verma',null,'Concept Of Physics','Dreamland', 1999, null , 'AVAILABLE'),
       (8,'Young Freedman',null,'University Physics','Sara', 2010, null , 'AVAILABLE'),
       (9,'Balagurusamy',null,'Introduction to Java','Rupa', 2012, null , 'AVAILABLE'),
       (10,'Sundaram RMD',null,'Operating System','Sara', 2005, null , 'AVAILABLE'),
       (11,'Willaim Stallings',null,'Computer Architecture','Balsam', 2015, null , 'AVAILABLE'),
       (12,'Alfred V Aho',null,'Compilers','Pearson', 2018, null , 'AVAILABLE'),
       (13,'Anand Kumar',null,'Fundamental of Digital Circuits','Balsam', 2002, null , 'AVAILABLE'),
       (14,'Wiley',null,'Social Enginnering','Pearson', 2008, null , 'AVAILABLE'),
       (15,'Solomons',null,'Organic Chemistry','Sara', 2016, null , 'AVAILABLE'),
       (16,'J K Gupta',null,'Theory of Machines','Pearson', 1994, null , 'AVAILABLE'),
       (17,'M Mahajan',null,'Industrial Engineering','Sara', 1896, null , 'AVAILABLE'),
       (18,'H S Vishwanath',null,'Materials of Construction','Rupa', 1895, null , 'AVAILABLE'),
       (19,'Luis Amador-Jimenez',null,'System Analysis','Balsam', 1994, null , 'AVAILABLE'),
       (20,'B L Thareja',null,'Electrical Machines','Pearson', 2016, null , 'AVAILABLE');

-- Spring first apply schema.sql then create tables with @Table annotation
--Schema.sql is for creating tables and data.sql is for inserting and updating data
