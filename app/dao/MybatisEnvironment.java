package dao;

/**
 * Created by alex on 3/15/15.
 */
public enum MybatisEnvironment {
    PRODUCTION("production"),
    TESTING("testing");

    protected final String string;

    MybatisEnvironment(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return this.string;
    }
}
