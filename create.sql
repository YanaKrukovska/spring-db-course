create table bookings
(
    id                 bigserial not null,
    amount             int8,
    booking_date       timestamp not null,
    until_date         timestamp not null,
    storage_id         int8      not null,
    storage_product_id int8      not null,
    primary key (id)
);
create table details
(
    id                 bigserial not null,
    amount             int8,
    storage_product_id int8      not null,
    primary key (id)
);
create table groups
(
    id          bigserial    not null,
    description varchar(255),
    name        varchar(255) not null,
    primary key (id)
);
create table orders
(
    id       bigserial    not null,
    customer varchar(255),
    status   varchar(255) not null,
    primary key (id)
);
create table orders_order_details
(
    orders_id        int8 not null,
    order_details_id int8 not null
);
create table products
(
    id           bigserial      not null,
    description  varchar(255),
    manufacturer varchar(255)   not null,
    name         varchar(255)   not null,
    price        numeric(19, 2) not null,
    group_id     int8           not null,
    primary key (id)
);
create table storage_products
(
    id         bigserial not null,
    amount     int8,
    product_id int8      not null,
    storage_id int8      not null,
    primary key (id)
);
create table storages
(
    id      bigserial    not null,
    address varchar(255) not null,
    primary key (id)
);
alter table groups
    add constraint UK_8mf0is8024pqmwjxgldfe54l7 unique (name);
alter table orders_order_details
    add constraint UK_22wlf6q0mc7mkuqp7bvm3dm2d unique (order_details_id);
alter table products
    add constraint UK_o61fmio5yukmmiqgnxf8pnavn unique (name);
alter table storages
    add constraint UK_90et8cryxnggky23insa5q4nl unique (address);
alter table bookings
    add constraint FKj1462w5m5kf3x9e7n4akrmym5 foreign key (storage_id) references storages;
alter table bookings
    add constraint FKobxxkbpdp8bagr61vcmfh93up foreign key (storage_product_id) references storage_products;
alter table details
    add constraint FKfwiao6wupi4tevhllot1e8wug foreign key (storage_product_id) references storage_products;
alter table orders_order_details
    add constraint FKla49xy8wbrufj39g6ntdlsbh5 foreign key (order_details_id) references details;
alter table orders_order_details
    add constraint FKpjk9623wyjqoyvueqb09eh2ws foreign key (orders_id) references orders;
alter table products
    add constraint FKtk0w0k1mrif467aywwbccg7oo foreign key (group_id) references groups;
alter table storage_products
    add constraint FK953dj703uq6wd4cje3lfm8n57 foreign key (product_id) references products;
alter table storage_products
    add constraint FKkh232rh1luhovfn50yasdgd4p foreign key (storage_id) references storages;
