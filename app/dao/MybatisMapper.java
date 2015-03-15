package dao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
/**
 * Created by alex on 12/22/14.
 */
public class MybatisMapper {
    protected final static String CONFIG_FILE = "mybatis.xml";
    protected static MybatisMapper instance = null;
    protected static SqlSessionFactory sqlSessionFactory = null;
    protected static MybatisEnvironment environment = null;

    protected MybatisMapper() {    // to avoid instantiation
        try {
            InputStream inputStream = Resources.getResourceAsStream(CONFIG_FILE);
            if(environment != null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment.toString());
            } else {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static MybatisMapper getInstance() {
        if(MybatisMapper.instance == null) MybatisMapper.instance = new MybatisMapper();
        org.apache.ibatis.logging.LogFactory.useSlf4jLogging();
        return MybatisMapper.instance;
    }

    public static MybatisMapper getInstance(MybatisEnvironment environment) {
        MybatisMapper.environment = environment;
        return getInstance();
    }

    public SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }



}