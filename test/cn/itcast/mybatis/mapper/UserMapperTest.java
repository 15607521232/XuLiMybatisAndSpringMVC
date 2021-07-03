package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.po.User;
import cn.itcast.mybatis.po.UserCustom;
import cn.itcast.mybatis.po.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception{
        //Mybatis的配置文件
        String resource = "SqlMapConfig.xml";

        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);


        //创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testFindUserById() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        //调用userMapper的方法
        User user = userMapper.findUserById(1);
        session.close();
        System.out.println(user);
    }

    @Test
    public void testfindUserByName() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        //调用userMapper的方法
        List<User> userList = userMapper.findUserByName("小明");
        System.out.println(userList);
        session.close();
    }

    @Test
    public void testfindUserList() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        //封装包装对象，设置查询条件
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        //userCustom.setUsername("小明");
        userQueryVo.setUserCustom(userCustom);

        //调用userMapper的方法
        List<UserCustom> userList = userMapper.findUserList(userQueryVo);
        System.out.println(userList);
        session.close();
    }

    @Test
    public void testfindUserCount() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        //封装包装对象，设置查询条件
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        //userCustom.setSex("1");
        userCustom.setUsername("小明");
        userQueryVo.setUserCustom(userCustom);

        //调用userMapper的方法
        int count = userMapper.findUserCount(userQueryVo);
        System.out.println(count);
        session.close();
    }

    @Test
    public void testfindUserByIdResultMap() throws Exception {
        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);

        //调用userMapper的方法
        User  user= userMapper.findUserByIdResultMap(1);
        System.out.println(user);
        session.close();
    }
}
