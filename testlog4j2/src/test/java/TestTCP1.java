import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhaopei
 * @create 2019-11-26 17:38
 */
public class TestTCP1 {
    /*
    服务端Socket的accept方法是阻塞的，只有socket中有数据的时候才消除阻塞往下执行。
    客户端在显示调用socket的shutdownOutput后若再写数据，就会有SocketException:Cannot send after socket shutdown: socket write error
    socket的shutdownOutput后服务端的socket将会读完数据，否则len = is.read(b)会一直不等于-1，即一直认为可能会有数据。
    那客户端和服务端怎么进行读写数据交互？要重新打开读写流？
    socket发送数据应该有缓冲大小，当缓冲大小或者时间到达（可能）将执行一次网络数据传输
    一端的Socket被关闭（或主动关闭或者因为异常退出而引起的关闭，未显示调用socket.close方法而直接进程停止），另一端仍发送数据，发送的第一个数据包引发该异常(Connect reset by peer)
     java.net.SocketException: Connection reset
        at java.net.SocketInputStream.read(SocketInputStream.java:210)
        at java.net.SocketInputStream.read(SocketInputStream.java:141)
        at java.net.SocketInputStream.read(SocketInputStream.java:127)
        at TestTCP1.client(TestTCP1.java:45)
        at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.lang.reflect.Method.invoke(Method.java:498)
        at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
        at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
        at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
        at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
        at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
        at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
        at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
        at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
        at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
        at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
        at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
        at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
        at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
        at com.intellij.junit4.JUnit4IdeaTestRunner.startRunnerWithArgs(JUnit4IdeaTestRunner.java:68)
        at com.intellij.rt.execution.junit.IdeaTestRunner$Repeater.startRunnerWithArgs(IdeaTestRunner.java:47)
        at com.intellij.rt.execution.junit.JUnitStarter.prepareStreamsAndStart(JUnitStarter.java:242)
        at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:70)

    Process finished with exit code 0

     */
    //客户端
    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 8989);
            os = socket.getOutputStream();
            byte[] a = new byte[4096 * 1000];
            for (int i = 0; i < a.length; i++) {
                a[i] = 'a';
            }
            os.write(a);
            os.flush();
//            Thread.currentThread().sleep(2000);
            //shutdownOutput():执行此方法，显式的告诉服务端发送完毕！
            socket.shutdownOutput();
//            socket.shutdownOutput();

            is = socket.getInputStream();
            byte[] b = new byte[20];
            int len;
            while ((len = is.read(b)) != -1) {
                String str = new String(b, 0, len);
                System.out.print(str);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }


    }

    //服务端
    @Test
    public void server() {
        ServerSocket ss = null;
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            ss = new ServerSocket(8989);
            s = ss.accept();
            int num = 10 / 0;
            is = s.getInputStream();
            byte[] b = new byte[20];
            int len;
            while ((len = is.read(b)) != -1) {
                String str = new String(b, 0, len);
                System.out.print(str);
            }
            os = s.getOutputStream();
            os.write("我已收到你的情意".getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("异常.....");
            System.exit(1);
            System.out.println("");
            System.out.println("服务端开始关闭socket");
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }
}
