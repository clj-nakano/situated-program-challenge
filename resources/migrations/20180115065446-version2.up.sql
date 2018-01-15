create type venue_type as enum ('physical', 'online');
--;;
alter table venues add column url text;
--;;
alter table venues add column venue_type venue_type default 'physical';
