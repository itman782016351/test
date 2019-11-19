/**
 * @author zhaopei
 * @create 2019-11-18 14:44
 */
public class FalseSharingTest {

    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());
    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(System.currentTimeMillis() - start);
        System.out.println(pointer);
    }
}

//参考https://www.jianshu.com/p/7758bb277985
//2、使用 @sun.misc.Contended 注解（java8），默认使用这个注解是无效的，需要在JVM启动参数加上-XX:-RestrictContended才会生效
class Pointer {
    volatile long x;
    // 1、long p1, p2, p3, p4, p5, p6, p7;
    volatile long y;
}
