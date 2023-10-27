-- -- -- -- -- -- -- -- -- Database -- -- -- -- -- -- -- -- -- --
DROP DATABASE IF EXISTS WasteManagement;
CREATE DATABASE WasteManagement;
USE WasteManagement;
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
-- Tables
-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --
-- Category
CREATE TABLE IF NOT EXISTS `category` (
        Category_Id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        Category_Name VARCHAR(30) NOT NULL
);

-- Application
CREATE TABLE IF NOT EXISTS `rs` (
    Category_Id INT NOT NULL,
    `Name` VARCHAR(15) NOT NULL,
    -- description is stored as an array
    `Description` LONGTEXT,
    FOREIGN KEY (`Category_Id`)
        REFERENCES `category` (`Category_Id`)
        ON UPDATE CASCADE,
    -- composite primary key
    PRIMARY KEY (Category_Id, `Name`)
);

-- Hubs
CREATE TABLE IF NOT EXISTS `hub` (
    Hub_ID INT NOT NULL AUTO_INCREMENT,
    Hub_Name VARCHAR(100) NOT NULL,
    Which_R VARCHAR(100) NOT NULL,
    Categories_List VARCHAR(1000) NOT NULL,
    Link VARCHAR(1000) NOT NULL,
    Address VARCHAR(1000) NOT NULL,
    -- geometry that represents a single location in coordinate space.
    -- Postcode required for editing purposes, (so it can be displayed without re-calling the geocaching api stuff)
    Postcode VARCHAR(10) NOT NULL,
    /** type of extended index that allows you to index a spatial column
    Indexes are used to find rows with specific column values quickly
    Allowing us to store the longitude and latitude**/
    Latitude DOUBLE NOT NULL,
    Longitude DOUBLE NOT NULL,
    Phone_Number VARCHAR(20) NOT NULL,
    PRIMARY KEY (Hub_ID)
);

-- Repair booking
CREATE TABLE IF NOT EXISTS `repair_booking`
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
        REFERENCES `hub` (`Hub_Id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Blogs
CREATE TABLE IF NOT EXISTS `blog`
(
    Blog_Id INT NOT NULL AUTO_INCREMENT,
    Blog_Title VARCHAR(50) NOT NULL,
    Username VARCHAR(20) ,
    Item_Name VARCHAR (40) ,
    Item_Category VARCHAR(13),
    -- This will store the image, BLOB is not being highlighted so I hope to god that it will work
    Item_Image LONGBLOB,
    Blog_Contact VARCHAR(50),
    Item_Description VARCHAR (1000) NOT NULL,
    PRIMARY KEY (Blog_Id)
);

-- Buy / Rent
create table if not exists `buy_rent` (
    item_Id int not null auto_increment,
    item_Name varchar(40) not null,
    username varchar (20) not null,
    email varchar(100) not null,
    description varchar(400) not null,
    price  DECIMAL(10,2) not null,
    category varchar(100) not null,
    item_Sale_Type varchar(10) not null,
    image LongBlob,
    primary key (item_Id)
);

create table if not exists `index_page` (
    index_Id int not null auto_increment,
    description varchar(700) not null,
    image1 LongBlob ,
    image2 LongBlob ,
    image3 LongBlob ,
    image4 LongBlob ,
    primary key (index_Id)
);

create table if not exists `groups`(
    id bigint auto_increment primary key,
    role varchar(20) not null
);

create table if not exists `users` (
    username varchar(50) not null primary key,
    `password` varchar(500) not null,
    enabled boolean not null
);

create table if not exists `authorities` (
    username varchar(60) not null,
    authority varchar(60) not null,
    constraint fk_authorities_users foreign key(username) references `users`(username)
);

create table if not exists group_members (
    id bigint auto_increment primary key,
    username varchar(60) not null ,
    group_id bigint not null,
    constraint fk_group_members_group foreign key (group_id) references `groups`(id)
);

-- Comment
CREATE TABLE IF NOT EXISTS `comment`(
    Comment_Id INT NOT NULL AUTO_INCREMENT,
    Blog_Id INT NOT NULL,
    Username VARCHAR(40) NOT NULL,
    Comment VARCHAR(1000) NOT NULL,
    PRIMARY KEY (Comment_Id),
    FOREIGN KEY (`Blog_Id`)
    REFERENCES `blog` (`Blog_Id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);