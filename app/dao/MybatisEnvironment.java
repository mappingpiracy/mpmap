package dao;

/**
 * Created by alex on 3/15/15.
 */
public enum MybatisEnvironment {
    PRODUCTION("production"),
    TESTING("testing");

    protected final String xmlName;

    MybatisEnvironment(String xmlName) {
        this.xmlName = xmlName;
    }

    @Override
    public String toString() {
        return this.xmlName;
    }
}
