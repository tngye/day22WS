-- drop database if exists
drop schema if exists rsvp_database;

-- create a new database
create schema rsvp_database;

-- select database
use rsvp_database;

-- create table
create table rsvp (
    email varchar(128) not null,
    name varchar(128) not null,
    phone varchar(16), 
    confirmation_date date,
    comments text(1000),
    
    primary key(email)
);