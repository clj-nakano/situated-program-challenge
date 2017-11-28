drop table groups_members;
--;;
alter table venues drop column group_id;
--;;
alter table meetups drop column group_id;
--;;
drop table groups;

