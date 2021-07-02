package cn.itcast.mybatis.first;

import cn.itcast.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisFirst {

    @Test
    public void findByIdTest() throws IOException {

        //Mybatis的配置文件
        String resource = "SqlMapConfig.xml";

        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);


        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);



        //通过工厂得到sqlSwssion
        SqlSession session = sqlSessionFactory.openSession();


        //通过SqlSession操作数据库
        User user = session.selectOne("test.findUserById",1);
        System.out.println(user);

        //释放资源
        session.close();
    }

    //根据用户名称模糊查询用户信息
    @Test
    public void findByUserNameTest() throws IOException {

        //Mybatis的配置文件
        String resource = "SqlMapConfig.xml";

        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);


        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);



        //通过工厂得到sqlSwssion
        SqlSession session = sqlSessionFactory.openSession();


        //通过SqlSession操作数据库
        List<User> userList = session.selectList("test.findUserByName","小明");

        for (int i = 0; i <userList.size() ; i++) {

            User user = userList.get(i);
            System.out.println(user);

        }

        //释放资源
        session.close();


    }

    //添加用户信息
    @Test
    public void InsertUserTest() throws IOException {

        //Mybatis的配置文件
        String resource = "SqlMapConfig.xml";

        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);


        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);



        //通过工厂得到sqlSwssion
        SqlSession session = sqlSessionFactory.openSession();
        //插入用户对象
        User user = new User();
        user.setUsername("苏彪");
        user.setBirthday(new Date());
        user.setAddress("甘肃定西");
        user.setSex("男");

        //通过SqlSession操作数据库
        session.insert("test.insertUser",user);

        //提交事务
        session.commit();

        //释放资源
        session.close();

    }

    //删除用户
    @Test
    public void deleteUser() throws IOException {
        //Mybatis的配置文件
        String resource = "SqlMapConfig.xml";

        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);


        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);



        //通过工厂得到sqlSwssion
        SqlSession session = sqlSessionFactory.openSession();

        session.delete("test.deleteUser",26);


        //提交事务
        session.commit();

        //释放资源
        session.close();


    }

    //更新用户
    @Test
    public void updateUser() throws IOException {
        //Mybatis的配置文件
        String resource = "SqlMapConfig.xml";

        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resource);


        //创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);



        //通过工厂得到sqlSwssion
        SqlSession session = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(24);
        user.setUsername("苏大彪");
        user.setBirthday(new Date());
        user.setAddress("甘肃定西");
        user.setSex("男");

        session.update("test.UpdateUser",user);


        //提交事务
        session.commit();

        //释放资源
        session.close();


    }
}
