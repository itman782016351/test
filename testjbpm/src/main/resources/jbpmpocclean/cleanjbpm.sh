echo 'start sleep...';
date;
sleep 20000;
echo 'sleep end...';
date;
echo 'start scp.sh...';
/usr/bin/expect ./scp.sh;
date;
echo 'start sql.sh...';
sh ./sql.sh;
echo 'sql.sh end...'
date;
