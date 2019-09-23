-- Create tables
create table t_user
(
    user_id          bigint(20)  not null auto_increment,
    username         varchar(50) not null,
    first_name       varchar(50),
    last_name        varchar(50),
    password         varchar(50),
    rating           int(11),
    date_created     datetime,
    date_updated     datetime,
    residing_country varchar(50),
    email_address    varchar(50),
    primary key (user_id)
);

create table t_author
(
    author_id bigint(20) auto_increment not null,
    primary key (author_id)
);

create table t_book
(
    book_id        bigint(20)   not null auto_increment,
    title          varchar(100) not null,
    book_condition varchar(15),
    image_url      varchar(250),
    goodreads_id   bigint(20),
    date_created   datetime,
    date_updated   datetime,
    user           bigint(20),
    year_published bigint(11),
    author         varchar(50),
    primary key (book_id),
    foreign key (user) references t_user (user_id)
);

create table t_book_review
(
    review_id    bigint(20) auto_increment not null,
    review       varchar(2000),
    stars        int(11),
    user         bigint(20)                not null,
    book         bigint(20)                not null,
    date_created datetime,
    date_updated datetime,
    primary key (review_id),
    foreign key (user) references t_user (user_id),
    foreign key (book) references t_book (book_id)
);

-- Insert user data
insert into t_user(username, first_name, last_name)
values ('johndoe', 'John', 'Doe');

insert into t_user(username, first_name, last_name)
values ('janedoe', 'Jane', 'Doe');


-- Insert book data
insert into t_book(book_condition, image_url, goodreads_id, title, user)
values ('MINT', 'https://images.gr-assets.com/books/1327931476m/816.jpg', 1166797, 'Cryptonomicon', 1);

insert into t_book(book_condition, image_url, goodreads_id, title, user)
values ('MINT', 'https://images.gr-assets.com/books/1401432508m/4099.jpg', 7809, 'The Pragmatic Programmer', 1);

insert into t_book(book_condition, image_url, goodreads_id, title, user)
values ('POOR', 'https://images.gr-assets.com/books/1281419771m/888628.jpg', 909457, 'Neuromancer', 2);


