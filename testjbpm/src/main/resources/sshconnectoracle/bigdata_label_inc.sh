#��ִ�л�����������
. $CRM_PATH/jobenv11g.sh

#�����Ƿ�ɹ�
testerror

cd $CRM_PATH/auto_task/bigdata_label_inc/

echo "������ʼʱ�䣺"`date +"%Y%m%d%H%M"`


today=`TZ=aaa16 date +%Y%m%d`
file1=INC_ASSET_LABEL_${today}.CSV
echo $file1
 
#��֤�Ƿ���ļ�
ftp -i -n<<!
open 10.5.238.144
user dashuju_pt dsj2016
cd prod
lcd $CRM_PATH/auto_task/bigdata_label_inc/
prompt off
get $file1
done
close
bye
!

if [ -f $file1 ]; then
  NLS_LANG=american_america.AL32UTF8
  sqlldr BATCH_DML/bdml2011@sieb8 control=bigdata_label_inc.ctl data=${file1}
  NLS_LANG=AMERICAN_AMERICA.ZHS16GBK
  sqlplus -L BATCH_DML/bdml2011@sieb8 @bigdata_label_inc.sql
fi


#�����Ƿ�ɹ�
#testerror
echo "���������ʱ�䣺"`date +"%Y%m%d %T"`


