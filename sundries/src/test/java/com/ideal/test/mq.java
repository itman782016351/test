package com.ideal.test;

import com.ibm.mq.*;

import java.io.IOException;

public class mq {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String hostName = "10.7.92.94";//主机名
            String channel = "SYSTEM.ADMIN.SVRCONN";//服务器连接通道名
            String qManager = "CSB_JA_POC";//队列管理器名
            String qName = "CSB.SHLTEORDER.TO.MQ2MQ.IN";//队列名
            int port = 1415;
            int ccsid = 1208;
            /* 设置MQEnvironment 属性以便客户机连接 */
            MQEnvironment.hostname = hostName;
            MQEnvironment.channel = channel;
            MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY,
                    MQC.TRANSPORT_MQSERIES_CLIENT);
            MQEnvironment.CCSID = ccsid;
            MQEnvironment.port = port;
            /* 连接到队列管理器 */
            MQQueueManager qMgr = new MQQueueManager(qManager);

            /* 设置打开选项以便打开用于输出的队列，如果队列管理器正在停止，我们也已设置了选项去应对不成功情况。 */
            int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;

            /* 打开队列 */
            MQQueue queue = qMgr.accessQueue(qName, 8208);

            /* 设置放置消息选项我们将使用默认设置 */
            MQPutMessageOptions pmo = new MQPutMessageOptions();

            /* 创建消息，MQMessage 类包含实际消息数据的数据缓冲区，和描述消息的所有MQMD 参数 */

            /* 创建消息缓冲区 */
            MQMessage outMsg = new MQMessage();

            /* 设置MQMD 格式字段 */
            outMsg.format = MQC.MQFMT_STRING;

            /* 准备用户数据消息 */
            String msgString = "Test Message from PtpSender program ";
            try {
                outMsg.writeString(msgString);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            /* 在队列上放置消息 */
            queue.put(outMsg, pmo);

            /* 提交事务处理 */
            qMgr.commit();

            System.out.println(" The message has been Successfully put！\n");

            /* 关闭队列和队列管理器对象 */
            queue.close();
            qMgr.disconnect();
        } catch (MQException ex) {
            System.out.println("An MQ Error Occurred: Completion Code is :\t"
                    + ex.completionCode + "\n\n The Reason Code is :\t"
                    + ex.reasonCode);
            ex.printStackTrace();
        } /*catch (Exception e) {
e.printStackTrace();
}*/
    }
}

