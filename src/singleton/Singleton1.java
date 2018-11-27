package singleton;

/**
 * 单例模式:
 * 饿汉法: 第一次引用该类的时候就创建实例
 * 参考: http://www.importnew.com/18872.html
 */
public class Singleton1 {
    private static Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getSingleton() {
        return singleton;
    }
}
