create table if not exists book (
    id bigint generated by default as identity,
    author varchar(255),
    name varchar(255),
    description varchar(255),
    price float(24),
    primary key (id)
);

create table if not exists attachment (
    id uuid default random_uuid() not null,
    content blob,
    content_type varchar(255),
    filename varchar(255),
    book_id bigint,
    primary key (id),
    foreign key (book_id) references book
);
