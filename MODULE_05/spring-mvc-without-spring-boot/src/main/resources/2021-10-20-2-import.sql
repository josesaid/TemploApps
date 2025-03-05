insert into category(name,description,image_path) values('Games','Chess, board games','/resources/images/categories/chess.jpg');
insert into category(name,description,image_path) values('Opera','Drama, comedy','/resources/images/categories/opera.jpg');
insert into category(name,description,image_path) values('Cards','Poker, rummy, uno, war','/resources/images/categories/cards.jpg');
insert into category(name,description,image_path) values('Trips','Long or short trips','/resources/images/categories/trip.jpg');

-- credentials: admin/admin, user/user, user2/user2
insert into app_user(email,is_admin,nick,password) values ('admin@test.com',TRUE,'admin','$2a$10$unslFdFmvylEOha/X1fM0uEcf1/Ro3/LWXgQKsC6qy3bQ4PJS3HEi');
insert into app_user(email,is_admin,nick,password) values ('user@test.com',FALSE,'user','$2a$10$G5yGA29nxzHRnuuiaMWsZeXmLQ/ir/0cIGWXi2WBROrJWlvjjeBla');
insert into app_user(email,is_admin,nick,password) values ('user2@test.com',FALSE,'user2','$2a$10$k/n4w0E08hSLBIq1I/.k4e1zdXCF/COdHmqF4BwhyBqw749iIXRVC');

--events
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('Do you want to play a chess game?','CyberMachina','/resources/images/events/chess.jpg',3,'Chess',now(),1,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('Chess!','CyberMachina','/resources/images/events/chess.jpg',4,'Chess',now(),2,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('Comedy - Rigoletto','Opera','/resources/images/events/rigoletto.jpg',99,'Theatre',now(),3,2);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('Juwenaliaa!','Teki','/resources/images/events/juwenalia.jpg',15,'Juwenalia',now(),1,4);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('1Board games...','CyberMachina ul.Wolna 15','/resources/images/events/neuroshima.jpg',4,'Neuroshima',now(),2,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('2Board games...','CyberMachina ul.Wolna 15','/resources/images/events/neuroshima.jpg',4,'Neuroshima',now(),2,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('3Board games...','CyberMachina ul.Wolna 15','/resources/images/events/neuroshima.jpg',4,'Neuroshima',now(),2,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('4Board games...','CyberMachina ul.Wolna 15','/resources/images/events/neuroshima.jpg',4,'Neuroshima',now(),2,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('5Board games...','CyberMachina ul.Wolna 15','/resources/images/events/neuroshima.jpg',4,'Neuroshima',now(),2,1);
insert into event(description,location,image_path,participants,name,target_date,created_by,category_id) values ('6Board games...','CyberMachina ul.Wolna 15','/resources/images/events/neuroshima.jpg',4,'Neuroshima',now(),2,1);


-- --user_events
insert into user_event(user_id,event_id) values (1,1);
insert into user_event(user_id,event_id) values (1,2);
insert into user_event(user_id,event_id) values (1,3);
insert into user_event(user_id,event_id) values (2,3);
insert into user_event(user_id,event_id) values (2,4);
insert into user_event(user_id,event_id) values (3,5);
