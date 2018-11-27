package singleton;

/**
 * 单例模式:
 * 静态内部类写法: 延迟加载和线程安全
 */
public class Singleton3 {
    private static class Holder {
        private static Singleton3 singleton = new Singleton3();
    }

    private Singleton3() {
    }

    public static Singleton3 getSingleton() {
        return Holder.singleton;
    }
}
