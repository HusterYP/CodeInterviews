package singleton;

/**
 * 单例模式:
 * 懒汉式, 双重检查锁
 * 注意这里需要使用volatile防止指令重排
 * 但是volatile是在JDK5才引入的, JDK5之前还是有问题
 */
public class Singleton2 {
    private static volatile Singleton2 singleton = null;

    private Singleton2() {
    }

    public static Singleton2 getSingleton() {
        if (singleton == null) {
            synchronized (Singleton2.class) {
                if (singleton == null) {
                    singleton = new Singleton2();
                }
            }
        }
        return singleton;
    }
}
