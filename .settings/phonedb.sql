create phonedb identified by "phonedb";
grant connect, resource to phonedb;
commit;

drop table person;
drop sequence person_id;

create table person(
    person_id number(5)
    ,name varchar2(30) not null
    ,hp varchar2(20)
    ,company varchar2(20)
    ,primary key(person_id)
);
create sequence seq_person_id
	increment by 1
	start with 1
	nocache
);

insert into person (
	values(seq_person_id.nextval, name, hp, company)
);

select person_id
	,name
	,hp
	,company
from person;

update person
set name = name
	,hp = hp
	,company = company
where person_id = person_id;

delete from person
where person_id = person_id;