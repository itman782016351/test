echo 'excute sql begin...';
mysql -hlocalhost -uroot -pnewpassword<<use JBPMPOC;
SELECT * FROM JBPM4_DEPLOYMENT t WHERE t.DBID_='3300001';
CALL delByDat('k');
exit
