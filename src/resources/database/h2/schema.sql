-- -- -- -- -- -- -- -- -- -- -- setup -- -- -- -- -- -- -- -- -- --
SET MODE MYSQL;
SET IGNORECASE = TRUE;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
-- Tables
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS `Rs`;
DROP TABLE IF EXISTS Hub;
DROP TABLE IF EXISTS `Repair_Booking`;
DROP TABLE IF EXISTS Blog;
DROP TABLE IF EXISTS `Buy_Rent`;
DROP TABLE IF EXISTS `Index_Page`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `Comment`;


-- Category
CREATE TABLE IF NOT EXISTS `Category`
(
    Category_Id INT NOT NULL AUTO_INCREMENT,
    Category_Name VARCHAR(50) NOT NULL,
    PRIMARY KEY (Category_Id)
) ENGINE = INNODB;

-- Use
CREATE TABLE IF NOT EXISTS `Rs`
(
    Category_Id INT NOT NULL,
    `Name` VARCHAR(15) NOT NULL,
    Description VARCHAR(1000),
    FOREIGN KEY (`Category_Id`)
        REFERENCES `Category` (`Category_Id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    PRIMARY KEY (Category_Id, `Name`)
) ENGINE = INNODB;

-- Hubs
CREATE TABLE IF NOT EXISTS `Hub`
(
    Hub_ID INT NOT NULL AUTO_INCREMENT,
    Hub_Name VARCHAR(100) NOT NULL,
    -- which r as in repair, recycling, reuse,...
    -- So they are stating where the hubs specialize in, now includes tip
    Which_R VARCHAR(100) NOT NULL,
    -- which category
    Categories_List VARCHAR(1000) NOT NULL,
    Link VARCHAR(1000) NOT NULL,
    Address VARCHAR(1000) NOT NULL,
    -- Postcode required for editing purposes, (so it can be displayed without re-calling the geocaching api stuff)
    Postcode VARCHAR(10) NOT NULL,
    -- geometry that represents a single location in coordinate space.
    Latitude DOUBLE NOT NULL,
    Longitude DOUBLE NOT NULL,
    Phone_Number VARCHAR(20) NOT NULL,
    PRIMARY KEY (Hub_ID)
) ENGINE = INNODB;

-- Repair booking
CREATE TABLE IF NOT EXISTS `Repair_Booking`
(
    Booking_Id INT NOT NULL AUTO_INCREMENT,
    Item_Name VARCHAR(100) NOT NULL,
    Item_Description VARCHAR(1000) NOT NULL,
    Hub_Id INT NOT NULL,
    Full_Name VARCHAR(100) NOT NULL,
    Email VARCHAR(320) NOT NULL,
    Address VARCHAR(1000) NOT NULL,
    PRIMARY KEY (Booking_Id),
    FOREIGN KEY (`Hub_Id`)
        REFERENCES `Hub` (`Hub_Id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = INNODB;

-- Blogs
CREATE TABLE IF NOT EXISTS `Blog`
(
    Blog_Id INT NOT NULL AUTO_INCREMENT,
    Blog_Title VARCHAR(50) ,
    Username VARCHAR(20) ,
    Item_Name VARCHAR (40) ,
    Item_Category VARCHAR(50),
    -- This will store the image, BLOB is not being highlighted so I hope to god that it will work
    Item_Image LONGBLOB,
    Blog_Contact VARCHAR(50),
    Item_Description VARCHAR (1000) NOT NULL,
    PRIMARY KEY (Blog_Id)
)ENGINE = INNODB;

-- Buy / Rent
create table if not exists `Buy_Rent` (
    item_Id int not null auto_increment,
    item_Name varchar(40) not null,
    username varchar (20) not null,
    email varchar(100) not null,
    description varchar(400) not null,
    price DECIMAL(10,2) not null,
    category varchar(100) not null,
    item_Sale_Type varchar(10) not null,
--     not sure why LongBlob isnt orange
    image LongBlob,
    primary key (item_Id)
)engine = innodb;

-- Buy / Rent
create table if not exists `Index_Page` (
    index_Id int not null auto_increment,
    description varchar(700) not null,
    image1 LongBlob ,
    image2 LongBlob ,
    image3 LongBlob ,
    image4 LongBlob ,
    primary key (index_Id)
)engine = innodb;


create table if not exists `users` (
    username varchar_ignorecase(50) not null primary key,
    `password` varchar_ignorecase(500) not null,
    enabled boolean not null
)engine = innodb;

create table if not exists `authorities` (
    username varchar_ignorecase(60) not null,
    authority varchar_ignorecase(60) not null,
    constraint fk_authorities_users foreign key(username) references users(username)

)engine = innodb;

create unique index ix_auth_username on authorities (username,authority);

-- Comment
CREATE TABLE IF NOT EXISTS `Comment`(
    Comment_Id INT NOT NULL AUTO_INCREMENT,
    Blog_Id INT NOT NULL,
    Username VARCHAR(40) NOT NULL,
    Comment VARCHAR(1000) NOT NULL,
    PRIMARY KEY (Comment_Id),
    FOREIGN KEY (`Blog_Id`)
        REFERENCES `Blog` (`Blog_Id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE = INNODB;

