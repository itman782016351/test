#先执行环境变量程序
. $CRM_PATH/jobenv11g.sh

#测试是否成功
testerror

cd $CRM_PATH/auto_task/bigdata_label_inc/

echo "批处理开始时间："`date +"%Y%m%d%H%M"`


today=`TZ=aaa16 date +%Y%m%d`
file1=INC_ASSET_LABEL_${today}.CSV
echo $file1
 
#验证是否空文件
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


#测试是否成功
#testerror
echo "批处理结束时间："`date +"%Y%m%d %T"`


