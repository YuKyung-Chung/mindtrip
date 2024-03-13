use product_schema;
drop table if exists product;

create table product
(
    product_id   int auto_increment,
    name varchar(50) not null,
    create_time datetime default now() not null,
    update_time datetime default now() not null,
    constraint product_pk
        primary key (product_id)
);