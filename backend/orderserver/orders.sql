use order_schema;
drop table if exists orders;

create table orders
(
    orders_id   int auto_increment,
    client_id   int                    not null,
    product_id  int                    not null,
    create_time datetime default now() not null,
    update_time datetime default now() not null,
    constraint orders_pk
        primary key (orders_id)
);