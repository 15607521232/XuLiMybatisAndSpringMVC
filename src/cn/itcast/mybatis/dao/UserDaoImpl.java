package cn.itcast.mybatis.dao;

import cn.itcast.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDaoImpl implements UserDao {

    // 需要向dao实现类中注入SqlSessionFactory
    // 这里通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        User user = session.selectOne("test.findUserById",id);

        //释放资源
        session.close();

        return user;
    }

    @Override
    public void insertUser(User user) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        session.insert("test.insertUser",user);

        //提交事务
        session.commit();

        //释放资源
        session.close();

    }

    @Override
    public void deleteUser(int id) throws Exception {
        SqlSession session = sqlSessionFactory.openSession();
        session.delete("test.deleteUser",id);


        //提交事务
        session.commit();

        //释放资源
        session.close();

    }
}
