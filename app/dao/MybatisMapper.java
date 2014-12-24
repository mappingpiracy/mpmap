package dao;

import models.Event;
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
    //private EventMapper eventMapper;

    public MybatisMapper() {
        try {
            this.inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            return;
        }
    }

    public SqlSession getSession() {
        this.session = sqlSessionFactory.openSession();
        return session;
    }

    public void closeSession() {
        this.session.close();
    }
}
