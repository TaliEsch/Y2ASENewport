-- TRUNCATE TABLE application;
-- TRUNCATE TABLE category;
-- TRUNCATE TABLE hub;
USE WasteManagement;



-- -- -- -- -- -- -- -- -- Category -- -- -- -- -- -- -- -- --
INSERT INTO category(Category_Name)
VALUES ('Clothing');
-- Select 'Hope this is running';
INSERT INTO category(Category_Name)
VALUES ('Cleaning');
INSERT INTO category(Category_Name)
VALUES ('Gardening & Outdoors');
INSERT INTO category(Category_Name)
VALUES ('Home & Kitchen');
INSERT INTO category(Category_Name)
VALUES ('Electronics');
INSERT INTO category(Category_Name)
VALUES ('Education');
INSERT INTO category(Category_Name)
VALUES ('Entertainment');
INSERT INTO category(Category_Name)
VALUES ('Pet supplies');
INSERT INTO category(Category_Name)
VALUES ('DIY & Tools');
INSERT INTO category(Category_Name)
VALUES ('Health & Beauty');
INSERT INTO category(Category_Name)
VALUES ('Food & Drink');
INSERT INTO category(Category_Name)
VALUES ('Other');

-- -- -- -- -- -- -- -- -- use -- -- -- -- -- -- -- -- --
INSERT INTO rs(Category_Id, `Name`,`Description`)
VALUES (1, 'Recycling', 'Take them to the recycling center');

INSERT INTO rs(Category_Id, `Name`,`Description`)
VALUES (1, 'Reuse', 'Turn your favourite t-shirts into a duvet cover, Turn your favourite t-shirts into a puppet');

INSERT INTO rs(Category_Id, `Name`,`Description`)
VALUES (1, 'Repair', 'You can patch a hole with a sewing design');


-- -- -- -- -- -- -- -- -- Hub -- -- -- -- -- -- -- -- --
INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Wastesavers Reuse Centre','Sell',
        'Electronics, Clothing, Food & Drinks, Other',
        'http://wastesavers.co.uk/',
        '138, 142 Chepstow Rd, Maindee, Newport NP19 8EG', 'NP19 8EG',
        51.589952207541465, -2.9791034567523265, '01154960762');

INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Wastesavers Recycling Limited','Recycle',
        'Clothing, Education, Home & Kitchen, Other',
        'http://wastesavers.co.uk/',
        'Esperanto Way, Newport NP19 0RD', 'NP19 0RD',
        51.57170809531799, -2.981394888375925, '01134960366');

INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Tradebe Gwent', 'sell',
        'Gardening & Camping, Home & Kitchen, Electronics, Entertainment, Pet supplies, DIY & Tools, Health & Beauty, Clothing, Other',
        'https://www.tradebe.co.uk/?utm_source=google&utm_medium=organic&utm_campaign=gmb-NP',
        'Park House/Corporation Rd, Newport NP19 4RD', 'NP19 4RD',
        51.56863602970646, -2.970020616329608, '01134960643');

INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Newport Repair Cafe','Repair',
        'Electronics, Other',
        'https://www.facebook.com/NewportRepairCafe/',
        'St. Marys Church, 1 St Marys Place, Newport NP20 1JG', 'NP20 1JG',
        51.574, -2.997, '02920180469');

INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('RE:MAKE Newport','Repair',
        'Electronics, Other',
        'https://www.remakenewport.org/',
        '26 Skinner St, Newport NP20 1HB', 'NP20 1HB',
        51.58823894778722, -2.99589051705125, '01633846806');

INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Newport Accident Repair Centre','Repair',
        'Electronics, Other',
        'https://m.facebook.com/newportaccidentrepaircentre/',
        'UNIT 1, BUSINESS CENTRE, Mill Parade, Newport NP20 2JR', 'NP20 2JR',
        51.57479530047593, -2.9859094802494983, '07700900964');

INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Newport MOT and Repair Centre Ltd','Repair',
        'Electronics, Other',
        'https://www.facebook.com/NewportMOT&RepairCentreLtd/',
        'Libeneth Road, Newport NP19 9AP', 'NP19 9AP',
        51.584310556278034, -2.9570452784247747, '08081570939');

-- -- -- -- -- -- -- -- -- tips in newport -- -- -- -- -- -- -- -- --
INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Newport Household Waste Recycling Centre','tip',
        'Gardening & Camping, Home & Kitchen, Electronics, Entertainment, Pet supplies, DIY & Tools, Health & Beauty, Clothing, Other',
        'https://www.newport.gov.uk/en/Waste-Recycling/Household-Waste-Recycling-Centre/Household-Waste-Recycling-Centre.aspx',
        'Docks Way, Newport NP20 2NS', 'NP20 2NS',
        51.56708282603663, -3.0030585530926257, '01633656656');

-- dummy one to see how it look
INSERT INTO hub(Hub_Name,Which_r,Categories_List,Link,`address`, Postcode, Latitude, Longitude, Phone_Number)
VALUES ('Tip Shop','tip',
        'Gardening & Camping, Home & Kitchen, Electronics, Entertainment, Pet supplies, DIY & Tools, Health & Beauty, Clothing, Other',
        'https://www.newport.gov.uk/en/Waste-Recycling/Household-Waste-Recycling-Centre/tip-Shop.aspx',
        'Docks Way, Newport NP20 2NS', 'NP20 2NS',
        51.56708282603663, -3.0030585530926257, '01633656656');

-- -- -- -- -- -- -- -- -- Blogs -- -- -- -- -- -- -- -- --
INSERT INTO blog(Blog_Title, Username, Item_Name, Item_Category, Item_Image, Item_Description)
VALUES ('How to make a duvet cover from an old t-shirt',
        'John Smith',
        'T-shirt',
        'Clothing',null,
        'Turn your favourite t-shirts into a duvet cover');

INSERT INTO blog(Blog_Title, Username, Item_Name, Item_Category, Item_Description)
VALUES ('Blog 2', 'Josh Demarco', 'Microwave Oven', 'Electronics', 'TESTING TESTING 123');

INSERT INTO blog(Blog_Title, Username, Item_Name, Item_Category, Item_Description)
VALUES ('Blog 3', 'Ayman', 'Microwave Oven', 'Electronics',
        'TESTING TESTING 123');

INSERT INTO blog(Blog_Title, Username, Item_Name, Item_Category, Item_Description)
VALUES ('Blog 4', null, null, null,'Minimum info needed to create a Blog');

-- -- -- -- -- -- -- -- -- Comments -- -- -- -- -- -- -- -- --
INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('Can you go into more depth on how this is done', 'John Smith', 1);

INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('This is weird', 'Jimmy Brown', 1);

INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('Has been tested', 'Daniel', 2);

INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('Or has it', 'Oliver', 2);

INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('Dune Dune Duuuuuuuuune', 'Ayman', 2);

INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('The microwave is working yay', 'Carl', 3);

INSERT INTO comment(Comment, Username, Blog_ID)
VALUES ('Thank you so much', 'Jack', 3);

-- -- -- -- -- -- -- -- -- Repair Booking -- -- -- -- -- -- -- -- --
INSERT INTO repair_booking(Item_Name, Item_Description,Hub_id, Full_Name, Email, Address)
VALUES ('TV', 'The TV is broken', 1,'John Smith', 'johnSmith@gmail.com', '1, Main Street, Newport, NP20 2NS');

INSERT INTO repair_booking(Item_Name, Item_Description,Hub_id, Full_Name, Email, Address)
VALUES ('Laptop', 'The Laptop is broken',2, 'David Smith', 'Smithk@gmail.com', '20, High Street, Newport, NP2 10NS');

INSERT INTO repair_booking(Item_Name, Item_Description,Hub_id, Full_Name, Email, Address)
VALUES ('Phone', 'The Phone is broken',3, 'Lenny Jones', 'JonesLen@gmail.com', '10, Westfield Street, Newport, NP12 2FS');

-- -- -- -- -- -- -- -- -- Buy_Rent -- -- -- -- -- -- -- -- --
INSERT INTO buy_rent(item_Id, item_Name, username, email, description, Category, price, item_Sale_Type)
VALUES (1, 'Sofa', 'David', 'David@gov.uk', 'Amazing sofa', 'Homeware', 1200.10, 1);

INSERT INTO buy_rent(item_Id, item_Name, username, email, description, Category, price, item_Sale_Type)
VALUES (2, 'Chair', 'Theresa', 'Theresa@gov.uk', 'Amazing Chair', 'Homeware', 1000.20, 0);

INSERT INTO buy_rent(item_Id, item_Name, username, email, description, Category, price, item_Sale_Type)
VALUES (3, 'Kitchen Set', 'Joe', 'Joe@gov.uk', 'Amazing Kitchen', 'Homeware', 12.20, 0);

insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (4, 'Item Name', 'jlinay3', 'ahiscoke3@youtu.be', 'Description', 'Electronics', 70.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (5, 'Item Name', 'lsetterfield4', 'otight4@liveinternet.ru', 'Description', 'Clothing', 39.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (6, 'Item Name', 'dgringley5', 'ageratasch5@hibu.com', 'Description', 'Gardening', 63.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (7, 'Item Name', 'bmcallister6', 'aaimer6@nbcnews.com', 'Description', 'Homeware', 8.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (8, 'Item Name', 'ssmeeth7', 'fludlom7@seesaa.net', 'Description', 'Gardening', 14.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (9, 'Item Name', 'lsedgeworth8', 'tlewtey8@webnode.com', 'Description', 'Clothing', 98.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (10, 'Item Name', 'agierke9', 'vbarkley9@moonfruit.com', 'Description', 'Electronics', 66.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (11, 'Item Name', 'rturbana', 'khalewooda@parallels.com', 'Description', 'Other', 28.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (12, 'Item Name', 'kbodimeadeb', 'jfarndonb@squidoo.com', 'Description', 'Gardening', 80.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (13, 'Item Name', 'fiwanowiczc', 'gpauntonc@miitbeian.gov.cn', 'Description', 'Other', 65.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (14, 'Item Name', 'catheyd', 'apelld@studiopress.com', 'Description', 'Homeware', 27.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (15, 'Item Name', 'jmowlinge', 'gburlesse@engadget.com', 'Description', 'Other', 47.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (16, 'Item Name', 'jfarensf', 'npailinf@phoca.cz', 'Description', 'Electronics', 84.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (17, 'Item Name', 'atarttg', 'epeaseyg@va.gov', 'Description', 'Homeware', 61.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (18, 'Item Name', 'cclinnickh', 'rmccuthaish@skype.com', 'Description', 'Homeware', 20.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (19, 'Item Name', 'thenkmanni', 'kgoldsbyi@amazon.de', 'Description', 'Cleaning', 38.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (20, 'Item Name', 'msmallj', 'rgatesj@exblog.jp', 'Description', 'Gardening', 54.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (21, 'Item Name', 'tmuffettk', 'dfitzsimonk@about.me', 'Description', 'Gardening', 60.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (22, 'Item Name', 'lboasel', 'mbendell@github.io', 'Description', 'Homeware', 69.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (23, 'Item Name', 'dkinsonm', 'bchristoffelsm@ycombinator.com', 'Description', 'Clothing', 38.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (24, 'Item Name', 'dstarkn', 'hcroninn@hao123.com', 'Description', 'Homeware', 3.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (25, 'Item Name', 'bcoverdillo', 'mgillanio@yellowbook.com', 'Description', 'Cleaning', 94.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (26, 'Item Name', 'abrehatp', 'ndavidmanp@lycos.com', 'Description', 'Clothing', 97.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (27, 'Item Name', 'cdudbridgeq', 'lbromeq@dmoz.org', 'Description', 'Clothing', 59.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (28, 'Item Name', 'ttrautr', 'jcannr@list-manage.com', 'Description', 'Gardening', 57.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (29, 'Item Name', 'dsiviors', 'aszymons@virginia.edu', 'Description', 'Cleaning', 30.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (30, 'Item Name', 'rmunceyt', 'jmeneret@squidoo.com', 'Description', 'Gardening', 65.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (31, 'Item Name', 'dbonnaireu', 'gaventu@squidoo.com', 'Description', 'Homeware', 83.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (32, 'Item Name', 'owarlandv', 'tauchterlonyv@bloglines.com', 'Description', 'Electronics', 10.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (33, 'Item Name', 'lvonoertzenw', 'fswindellw@barnesandnoble.com', 'Description', 'Electronics', 32.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (34, 'Item Name', 'glammertsx', 'kworhamx@homestead.com', 'Description', 'Entertainment', 100.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (35, 'Item Name', 'fzapateroy', 'hcullrfordy@slideshare.net', 'Description', 'Homeware', 31.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (36, 'Item Name', 'mgatemanz', 'dwerendellz@loc.gov', 'Description', 'Homeware', 53.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (37, 'Item Name', 'lmaccoveney10', 'lhooks10@umich.edu', 'Description', 'Clothing', 1.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (38, 'Item Name', 'agrayer11', 'botto11@cafepress.com', 'Description', 'Homeware', 3.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (39, 'Item Name', 'relden12', 'wgosden12@census.gov', 'Description', 'Cleaning', 65.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (40, 'Item Name', 'dferrierio13', 'apittson13@google.com.au', 'Description', 'Cleaning', 94.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (41, 'Item Name', 'ecovotti14', 'ddracey14@paypal.com', 'Description', 'Electronics', 1.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (42, 'Item Name', 'wmahon15', 'slissenden15@virginia.edu', 'Description', 'Electronics', 9.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (43, 'Item Name', 'dvause16', 'sgodding16@paypal.com', 'Description', 'Homeware', 66.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (44, 'Item Name', 'athurlbourne17', 'hfigurski17@google.ru', 'Description', 'Gardening', 36.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (45, 'Item Name', 'llebarre18', 'itabor18@yelp.com', 'Description', 'Electronics', 35.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (46, 'Item Name', 'vrudolfer19', 'labrahams19@upenn.edu', 'Description', 'Gardening', 55.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (47, 'Item Name', 'cfinlow1a', 'mhune1a@diigo.com', 'Description', 'Clothing', 89.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (48, 'Item Name', 'kcastleton1b', 'bockenden1b@redcross.org', 'Description', 'Entertainment', 85.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (49, 'Item Name', 'spilgrim1c', 'aburdess1c@quantcast.com', 'Description', 'Cleaning', 63.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (50, 'Item Name', 'etrahear1d', 'cdows1d@weibo.com', 'Description', 'Clothing', 4.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (51, 'Item Name', 'sclimpson1e', 'tokielt1e@springer.com', 'Description', 'Other', 54.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (52, 'Item Name', 'efilkov1f', 'bbramley1f@columbia.edu', 'Description', 'Clothing', 33.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (53, 'Item Name', 'mgoodsall1g', 'kstillwell1g@dailymail.co.uk', 'Description', 'Other', 25.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (54, 'Item Name', 'dmunslow1h', 'ccopcott1h@zimbio.com', 'Description', 'Electronics', 59.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (55, 'Item Name', 'bjelliman1i', 'vwoosnam1i@usnews.com', 'Description', 'Gardening', 24.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (56, 'Item Name', 'tyakebovitch1j', 'kpowter1j@imgur.com', 'Description', 'Clothing', 62.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (57, 'Item Name', 'bkitcher1k', 'jculligan1k@mediafire.com', 'Description', 'Other', 19.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (58, 'Item Name', 'tchatters1l', 'ocremin1l@edublogs.org', 'Description', 'Homeware', 33.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (59, 'Item Name', 'ghazley1m', 'dchin1m@themeforest.net', 'Description', 'Gardening', 66.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (60, 'Item Name', 'pdotson1n', 'dhadfield1n@bluehost.com', 'Description', 'Other', 71.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (61, 'Item Name', 'cadlem1o', 'shingeley1o@ihg.com', 'Description', 'Gardening', 8.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (62, 'Item Name', 'rspeddin1p', 'astollsteimer1p@liveinternet.ru', 'Description', 'Electronics', 61.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (63, 'Item Name', 'csermin1q', 'cfennelly1q@vistaprint.com', 'Description', 'Entertainment', 13.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (64, 'Item Name', 'frosenfarb1r', 'lwiggall1r@dagondesign.com', 'Description', 'Electronics', 84.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (65, 'Item Name', 'shuxster1s', 'krolingson1s@oakley.com', 'Description', 'Homeware', 98.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (66, 'Item Name', 'lrounds1t', 'gnutting1t@hubpages.com', 'Description', 'Other', 75.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (67, 'Item Name', 'lcomusso1u', 'abaldree1u@bravesites.com', 'Description', 'Clothing', 46.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (68, 'Item Name', 'agrimmett1v', 'aluty1v@devhub.com', 'Description', 'Gardening', 22.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (69, 'Item Name', 'cbiglin1w', 'rhowerd1w@lulu.com', 'Description', 'Cleaning', 14.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (70, 'Item Name', 'dwarlaw1x', 'hbunting1x@nbcnews.com', 'Description', 'Homeware', 4.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (71, 'Item Name', 'ckeston1y', 'glefranc1y@flickr.com', 'Description', 'Homeware', 24.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (72, 'Item Name', 'lmorley1z', 'vradborne1z@squarespace.com', 'Description', 'Cleaning', 35.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (73, 'Item Name', 'bsoares20', 'cmason20@cdc.gov', 'Description', 'Electronics', 23.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (74, 'Item Name', 'hgilhoolie21', 'jclelle21@godaddy.com', 'Description', 'Entertainment', 15.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (75, 'Item Name', 'mfollin22', 'spitsall22@miibeian.gov.cn', 'Description', 'Cleaning', 59.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (76, 'Item Name', 'chawkshaw23', 'bsweedland23@stumbleupon.com', 'Description', 'Gardening', 29.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (77, 'Item Name', 'wcollinette24', 'kbertelmot24@people.com.cn', 'Description', 'Homeware', 52.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (78, 'Item Name', 'rclow25', 'rsheahan25@oracle.com', 'Description', 'Entertainment', 45.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (79, 'Item Name', 'acuttin26', 'mclifforth26@examiner.com', 'Description', 'Gardening', 85.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (80, 'Item Name', 'wmanns27', 'mpulfer27@biblegateway.com', 'Description', 'Electronics', 76.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (81, 'Item Name', 'mstammers28', 'dkelleher28@howstuffworks.com', 'Description', 'Entertainment', 53.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (82, 'Item Name', 'lmanuele29', 'ochristene29@gnu.org', 'Description', 'Homeware', 24.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (83, 'Item Name', 'qgounel2a', 'mmackibbon2a@tiny.cc', 'Description', 'Clothing', 31.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (84, 'Item Name', 'bkasting2b', 'edeambrosi2b@networksolutions.com', 'Description', 'Cleaning', 35.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (85, 'Item Name', 'sibbett2c', 'ebrundale2c@ucsd.edu', 'Description', 'Gardening', 36.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (86, 'Item Name', 'rsummerlie2d', 'ghallaways2d@hp.com', 'Description', 'Entertainment', 88.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (87, 'Item Name', 'gdutnell2e', 'cjansson2e@e-recht24.de', 'Description', 'Other', 75.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (88, 'Item Name', 'nmansuer2f', 'tfarady2f@livejournal.com', 'Description', 'Homeware', 57.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (89, 'Item Name', 'amoulds2g', 'oabramovitz2g@stanford.edu', 'Description', 'Other', 56.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (90, 'Item Name', 'ltabourel2h', 'lhurleston2h@over-blog.com', 'Description', 'Electronics', 57.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (91, 'Item Name', 'jmosedill2i', 'smcgarry2i@eventbrite.com', 'Description', 'Cleaning', 5.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (92, 'Item Name', 'ljohnys2j', 'sseamon2j@epa.gov', 'Description', 'Gardening', 33.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (93, 'Item Name', 'abellin2k', 'cwaycot2k@newyorker.com', 'Description', 'Cleaning', 62.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (94, 'Item Name', 'ekemet2l', 'cales2l@themeforest.net', 'Description', 'Other', 40.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (95, 'Item Name', 'jpearcehouse2m', 'lalwin2m@wikispaces.com', 'Description', 'Other', 49.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (96, 'Item Name', 'skibbe2n', 'bbordes2n@europa.eu', 'Description', 'Entertainment', 79.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (97, 'Item Name', 'ccockin2o', 'ilabadini2o@reddit.com', 'Description', 'Clothing', 49.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (98, 'Item Name', 'cmathonnet2p', 'gsygrove2p@nationalgeographic.com', 'Description', 'Gardening', 77.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (99, 'Item Name', 'acarnock2q', 'ebeavan2q@google.nl', 'Description', 'Entertainment', 46.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (100, 'Item Name', 'acollecott2r', 'tspennock2r@omniture.com', 'Description', 'Cleaning', 55.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (101, 'Item Name', 'mcrebbin2s', 'hhazell2s@si.edu', 'Description', 'Clothing', 33.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (102, 'Item Name', 'hstanyard2t', 'tgawen2t@thetimes.co.uk', 'Description', 'Other', 85.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (103, 'Item Name', 'ebellenie2u', 'elarkcum2u@nbcnews.com', 'Description', 'Homeware', 1.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (104, 'Item Name', 'imckyrrelly2v', 'gcuberley2v@narod.ru', 'Description', 'Gardening', 27.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (105, 'Item Name', 'mmelloi2w', 'nleaney2w@ustream.tv', 'Description', 'Homeware', 30.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (106, 'Item Name', 'koliddy2x', 'mtredger2x@pinterest.com', 'Description', 'Entertainment', 64.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (107, 'Item Name', 'costrich2y', 'bsymonds2y@theatlantic.com', 'Description', 'Homeware', 99.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (108, 'Item Name', 'shansbury2z', 'smartine2z@yellowbook.com', 'Description', 'Entertainment', 83.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (109, 'Item Name', 'glintall30', 'sbasili30@intel.com', 'Description', 'Cleaning', 42.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (110, 'Item Name', 'eleger31', 'nobruen31@dailymail.co.uk', 'Description', 'Clothing', 70.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (111, 'Item Name', 'callmen32', 'lberntssen32@twitter.com', 'Description', 'Entertainment', 95.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (112, 'Item Name', 'emaunton33', 'dcattle33@prlog.org', 'Description', 'Gardening', 2.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (113, 'Item Name', 'aarndtsen34', 'sjenne34@twitpic.com', 'Description', 'Entertainment', 18.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (114, 'Item Name', 'kdankersley35', 'gglanfield35@sakura.ne.jp', 'Description', 'Other', 42.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (115, 'Item Name', 'tstreeten36', 'iwrathmell36@dot.gov', 'Description', 'Entertainment', 23.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (116, 'Item Name', 'hduffell37', 'mwoollett37@live.com', 'Description', 'Electronics', 74.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (117, 'Item Name', 'smacpaik38', 'lponsford38@adobe.com', 'Description', 'Other', 67.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (118, 'Item Name', 'ybickford39', 'jwolfendale39@exblog.jp', 'Description', 'Entertainment', 3.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (119, 'Item Name', 'mipgrave3a', 'tarnli3a@hugedomains.com', 'Description', 'Entertainment', 63.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (120, 'Item Name', 'rludlom3b', 'tmorais3b@fastcompany.com', 'Description', 'Other', 80.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (121, 'Item Name', 'pbrumble3c', 'bcicccitti3c@cargocollective.com', 'Description', 'Gardening', 71.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (122, 'Item Name', 'dgascoine3d', 'dcleever3d@goodreads.com', 'Description', 'Cleaning', 34.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (123, 'Item Name', 'wscartifield3e', 'bremirez3e@xinhuanet.com', 'Description', 'Electronics', 16.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (124, 'Item Name', 'hmckmurrie3f', 'kelderbrant3f@berkeley.edu', 'Description', 'Electronics', 36.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (125, 'Item Name', 'slinck3g', 'acoon3g@delicious.com', 'Description', 'Cleaning', 17.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (126, 'Item Name', 'ftwohig3h', 'swestcar3h@oaic.gov.au', 'Description', 'Electronics', 2.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (127, 'Item Name', 'aclausewitz3i', 'gshoutt3i@weebly.com', 'Description', 'Cleaning', 91.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (128, 'Item Name', 'jvergine3j', 'arobert3j@github.io', 'Description', 'Homeware', 40.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (129, 'Item Name', 'rdunican3k', 'sbartlam3k@jugem.jp', 'Description', 'Clothing', 96.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (130, 'Item Name', 'rpepler3l', 'ffaley3l@ucoz.ru', 'Description', 'Gardening', 55.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (131, 'Item Name', 'mscougall3m', 'aclemens3m@comcast.net', 'Description', 'Gardening', 18.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (132, 'Item Name', 'mkeasy3n', 'mbrecher3n@cyberchimps.com', 'Description', 'Clothing', 72.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (133, 'Item Name', 'sruberti3o', 'rbrockelsby3o@symantec.com', 'Description', 'Clothing', 94.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (134, 'Item Name', 'bpennycook3p', 'llindl3p@google.ru', 'Description', 'Other', 86.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (135, 'Item Name', 'fmonkley3q', 'tpaulat3q@posterous.com', 'Description', 'Entertainment', 92.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (136, 'Item Name', 'eplayer3r', 'bcarty3r@usgs.gov', 'Description', 'Other', 30.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (137, 'Item Name', 'lmeynell3s', 'wgreaves3s@wufoo.com', 'Description', 'Clothing', 57.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (138, 'Item Name', 'bandrault3t', 'mshoreman3t@aol.com', 'Description', 'Gardening', 29.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (139, 'Item Name', 'sstrelitzer3u', 'mchattell3u@kickstarter.com', 'Description', 'Other', 4.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (140, 'Item Name', 'baggs3v', 'sroskam3v@cloudflare.com', 'Description', 'Electronics', 94.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (141, 'Item Name', 'icisson3w', 'dlockett3w@twitpic.com', 'Description', 'Other', 44.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (142, 'Item Name', 'rjosh3x', 'unowak3x@barnesandnoble.com', 'Description', 'Electronics', 77.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (143, 'Item Name', 'ccromer3y', 'mpickthorn3y@spiegel.de', 'Description', 'Clothing', 51.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (144, 'Item Name', 'cschlagtmans3z', 'gklementz3z@qq.com', 'Description', 'Gardening', 6.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (145, 'Item Name', 'ilebbon40', 'irobbe40@google.nl', 'Description', 'Cleaning', 64.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (146, 'Item Name', 'gcharon41', 'bwolfers41@exblog.jp', 'Description', 'Cleaning', 33.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (147, 'Item Name', 'aschindler42', 'jviggars42@apple.com', 'Description', 'Other', 34.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (148, 'Item Name', 'ndunning43', 'dsinnett43@webs.com', 'Description', 'Clothing', 19.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (149, 'Item Name', 'rworg44', 'kworrell44@unc.edu', 'Description', 'Entertainment', 10.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (150, 'Item Name', 'vwardingly45', 'mdeegin45@yellowbook.com', 'Description', 'Other', 89.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (151, 'Item Name', 'eleitch46', 'ctaynton46@wired.com', 'Description', 'Cleaning', 48.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (152, 'Item Name', 'adumbrill47', 'wclarkewilliams47@dagondesign.com', 'Description', 'Gardening', 27.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (153, 'Item Name', 'hvardy48', 'mgalvan48@cnet.com', 'Description', 'Homeware', 56.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (154, 'Item Name', 'gboas49', 'cosgerby49@webeden.co.uk', 'Description', 'Homeware', 46.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (155, 'Item Name', 'mbauduccio4a', 'mdavydychev4a@illinois.edu', 'Description', 'Other', 84.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (156, 'Item Name', 'btibbits4b', 'lbromwich4b@cmu.edu', 'Description', 'Entertainment', 67.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (157, 'Item Name', 'ggabrieli4c', 'gbragger4c@360.cn', 'Description', 'Gardening', 42.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (158, 'Item Name', 'bscurrell4d', 'higlesias4d@plala.or.jp', 'Description', 'Other', 56.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (159, 'Item Name', 'jgolde4e', 'gbroadbury4e@squidoo.com', 'Description', 'Entertainment', 44.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (160, 'Item Name', 'brosenfelt4f', 'eblondin4f@de.vu', 'Description', 'Gardening', 56.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (161, 'Item Name', 'nmcharg4g', 'psandyford4g@stumbleupon.com', 'Description', 'Clothing', 74.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (162, 'Item Name', 'tcocks4h', 'rilive4h@nhs.uk', 'Description', 'Gardening', 6.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (163, 'Item Name', 'reunson4i', 'aboulder4i@msn.com', 'Description', 'Clothing', 67.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (164, 'Item Name', 'lcellone4j', 'cives4j@parallels.com', 'Description', 'Other', 29.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (165, 'Item Name', 'nhaggerston4k', 'obeecker4k@skyrock.com', 'Description', 'Gardening', 12.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (166, 'Item Name', 'kmcbryde4l', 'tbobasch4l@freewebs.com', 'Description', 'Homeware', 83.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (167, 'Item Name', 'cellesworthe4m', 'bleader4m@pen.io', 'Description', 'Electronics', 93.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (168, 'Item Name', 'dpepye4n', 'relvy4n@google.com.hk', 'Description', 'Cleaning', 38.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (169, 'Item Name', 'sdayment4o', 'lgavaghan4o@friendfeed.com', 'Description', 'Homeware', 28.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (170, 'Item Name', 'zflynn4p', 'rbroadley4p@ustream.tv', 'Description', 'Entertainment', 41.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (171, 'Item Name', 'kcommuzzo4q', 'drontree4q@over-blog.com', 'Description', 'Homeware', 35.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (172, 'Item Name', 'nvedenichev4r', 'ameechan4r@washingtonpost.com', 'Description', 'Homeware', 31.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (173, 'Item Name', 'mwingrove4s', 'kjamrowicz4s@bbc.co.uk', 'Description', 'Cleaning', 86.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (174, 'Item Name', 'cmcquin4t', 'kflawn4t@noaa.gov', 'Description', 'Cleaning', 27.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (175, 'Item Name', 'kendon4u', 'nvitler4u@dailymotion.com', 'Description', 'Homeware', 42.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (176, 'Item Name', 'jpoure4v', 'mmeah4v@123-reg.co.uk', 'Description', 'Other', 19.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (177, 'Item Name', 'ecanas4w', 'dbrelsford4w@comsenz.com', 'Description', 'Other', 45.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (178, 'Item Name', 'rjanak4x', 'gsignore4x@e-recht24.de', 'Description', 'Gardening', 4.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (179, 'Item Name', 'aandraud4y', 'lspolton4y@ucoz.com', 'Description', 'Homeware', 7.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (180, 'Item Name', 'fantonik4z', 'jnolda4z@360.cn', 'Description', 'Homeware', 48.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (181, 'Item Name', 'apaal50', 'ifawson50@reuters.com', 'Description', 'Clothing', 53.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (182, 'Item Name', 'omccheyne51', 'gmaggiori51@blinklist.com', 'Description', 'Gardening', 94.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (183, 'Item Name', 'wmeneyer52', 'cdominicacci52@com.com', 'Description', 'Electronics', 74.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (184, 'Item Name', 'lshrimpling53', 'aheard53@deliciousdays.com', 'Description', 'Entertainment', 43.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (185, 'Item Name', 'rcubin54', 'cpassler54@biblegateway.com', 'Description', 'Electronics', 2.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (186, 'Item Name', 'sbello55', 'bmaseres55@google.ca', 'Description', 'Gardening', 96.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (187, 'Item Name', 'rbeton56', 'jloudon56@sciencedirect.com', 'Description', 'Homeware', 90.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (188, 'Item Name', 'smartinot57', 'rszantho57@imageshack.us', 'Description', 'Gardening', 81.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (189, 'Item Name', 'tdowdney58', 'yberrick58@posterous.com', 'Description', 'Clothing', 9.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (190, 'Item Name', 'nmacgillreich59', 'tgilhespy59@linkedin.com', 'Description', 'Cleaning', 81.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (191, 'Item Name', 'frudgard5a', 'bburghall5a@about.com', 'Description', 'Homeware', 71.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (192, 'Item Name', 'imarran5b', 'rgrimwad5b@state.tx.us', 'Description', 'Entertainment', 56.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (193, 'Item Name', 'wstrainge5c', 'ewhile5c@constantcontact.com', 'Description', 'Electronics', 59.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (194, 'Item Name', 'nhubbuck5d', 'pgheorghe5d@paginegialle.it', 'Description', 'Homeware', 90.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (195, 'Item Name', 'cchesters5e', 'mbruneau5e@buzzfeed.com', 'Description', 'Entertainment', 27.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (196, 'Item Name', 'jmuffin5f', 'aillesley5f@seattletimes.com', 'Description', 'Clothing', 92.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (197, 'Item Name', 'jjoyes5g', 'lbresner5g@youtube.com', 'Description', 'Cleaning', 67.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (198, 'Item Name', 'pheimes5h', 'ddegoy5h@state.gov', 'Description', 'Cleaning', 44.00, 0);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (199, 'Item Name', 'ehallwood5i', 'jtennant5i@weather.com', 'Description', 'Electronics', 62.00, 1);
insert into buy_rent (item_id, item_Name, username, email, description, category, price, item_Sale_Type) values (200, 'Item Name', 'hmarch5j', 'adutnall5j@auda.org.au', 'Description', 'Other', 71.00, 1);
