

--复制一样数据
insert into mer_certificate t (t.id,t.cer_seq,t.cer_start,cer_over,t.cer_type,t.cer_status,t.create_time,t.mer_id,t.cer_data)
  (select 4 as id, cer_seq,cer_start,cer_over,cer_type,cer_status,create_time, '000001110100000002' as mer_id,cer_data
from mer_certificate t where id = 2);
commit;