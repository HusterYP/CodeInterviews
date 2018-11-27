package singleton;

/**
 * 单例模式:
 * 枚举
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
