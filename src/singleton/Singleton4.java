package singleton;

/**
 * 单例模式:
 * 枚举
 * 使用枚举除了线程安全和防止反射强行调用构造器之外，
 * 还提供了自动序列化机制，防止反序列化的时候创建新的对象
 * 因此，Effective Java推荐尽可能地使用枚举来实现单例
 */
public enum Singleton4 {
    SINGLETON;
    private String member; // 举例的成员变量

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
