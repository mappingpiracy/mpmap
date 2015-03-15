package dao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
/**
 * Created by alex on 12/22/14.
 */
public class MybatisMapper {
    protected String resource = "mybatis.xml";
    protected InputStream inputStream;
    protected SqlSessionFactory sqlSessionFactory;
//    protected SqlSession session;

    public MybatisMapper() {
        try {
            this.inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
    public SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
    public void closeSession() {
//        this.session.close();
    }
}