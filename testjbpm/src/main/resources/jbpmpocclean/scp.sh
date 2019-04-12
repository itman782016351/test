#!/usr/bin/expect -f
set timeout 3600
spawn sudo scp -rC   /esb/mysql/JBPMPOC csb@10.7.95.107:~/jbpm
#返回信息匹配    
 expect { 
#第一次ssh连接会提示yes/no,继续                
 "*yes/no" { send "yes\r"; exp_continue}  
#出现密码提示,发送密码
 "*Password:" { send "csb4g\r" }      
 }
expect eof
