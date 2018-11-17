select to_char(fecha,'AAAAMM'),count(*),'Primitiva'
 from pri_sorteo
where tipo='P'
group by to_char(fecha,'AAAAMM')
union 
select to_char(fecha,'AAAAMM'),count(*),'Bonoloto' 
from pri_sorteo
where tipo='B'
group by to_char(fecha,'AAAAMM');
