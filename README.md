# Notes-App-API

- Download and install Redis on your pc.

- Download and install MySql on your pc.

- Import APIs file from resources in postman.

MySql Configuration:

Run the following queries for initial setup:
1. create database test;
2. use test;
3. create table notedata (id integer auto increment not null primary key, title varchar(50), body varchar(255));
4. insert into notedata (title,body) values("Grocery Run", "Pick up fresh produce, dairy, and essentials from the local supermarket before evening.");
5. insert into notedata (title,body) values("Workout Break", "Take a 30-minute brisk walk or do a quick home workout session for a healthy break.");
6. insert into notedata (title,body) values("Email Responses", "Reply to important emails, addressing queries and setting follow-up meetings as needed.");
7. insert into notedata (title,body) values("Read Article", "Spend 20 minutes reading a professional development article to stay updated in the industry.");
8. insert into notedata (title,body) values("Project Update Call", "Schedule and prepare for a brief team call to update progress on the ongoing project.");
9. insert into notedata (title,body) values("Mindfulness Meditation", "Dedicate 10 minutes to practice mindfulness meditation for mental clarity and relaxation.");

Configure credentials in "resources -> application.properties"