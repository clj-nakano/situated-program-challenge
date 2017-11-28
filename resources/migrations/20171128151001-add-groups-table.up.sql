create table groups (
   id            serial primary key, 
   name          text,
   created_at    timestamp
);
--;;
create index groups_name on groups (name);
--;;
alter table meetups add column group_id int;
--;;
alter table venues add column group_id int;
--;;
create table groups_members (
    group_id    int,
    member_id   int,
    primary key (group_id, member_id)
);


