

--复制一样数据
insert into mer_certificate t (t.id,t.cer_seq,t.cer_start,cer_over,t.cer_type,t.cer_status,t.create_time,t.mer_id,t.cer_data)
  (select 4 as id, cer_seq,cer_start,cer_over,cer_type,cer_status,create_time, '000001110100000002' as mer_id,cer_data
from mer_certificate t where id = 2);
commit;


--查找会话
SELECT object_name, machine, s.sid, s.serial#
FROM gv$locked_object l, dba_objects o, gv$session s
WHERE l.object_id　= o.object_id
AND l.session_id = s.sid;
--kill 会话
ALTER system kill session '52, 11825';


--添加字段
alter table pp_order add(remark varchar(200));
COMMENT ON COLUMN PP_ORDER.remark IS '自定义保留域';


-- 删除表  序列
select 'drop table '||table_name||';'
from cat
where table_type='TABLE' ;

-- 删除表  序列
select 'drop sequence ' || sequence_name||';'||chr(13)||chr(10) from user_sequences;
