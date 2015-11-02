insert into t_user (user_id, user_name) values(null, 'bob');
insert into t_user (user_id, user_name) values(null, 'jack');
insert into t_user (user_id, user_name) values(null, 'jim');
insert into t_user (user_id, user_name) values(null, 'lisa');

insert into t_user_detail (user_id, gender, addr) values(1, 'M', 'address A');
insert into t_user_detail (user_id, gender, addr) values(2, 'M', 'address B');
insert into t_user_detail (user_id, gender, addr) values(3, 'M', 'address C');
insert into t_user_detail (user_id, gender, addr) values(4, 'F', 'address D');

insert into t_project (prj_id, prj_name) values(null, 'awesome project A');
insert into t_project (prj_id, prj_name) values(null, 'awesome project B');

insert into t_projects (prj_id, user_id) values(1, 1);
insert into t_projects (prj_id, user_id) values(2, 1);
insert into t_projects (prj_id, user_id) values(1, 2);
insert into t_projects (prj_id, user_id) values(2, 3);
