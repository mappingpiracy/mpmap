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

    private String resource = "mybatis.xml";
    private InputStream inputStream;
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;

    public MybatisMapper() {
        try {
            this.inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            this.session = sqlSessionFactory.openSession();
        } catch (Exception e) {

        }
    }

    public SqlSession getSession() {
        return session;
    }

    public void closeSession() {
        this.session.close();
    }
}
