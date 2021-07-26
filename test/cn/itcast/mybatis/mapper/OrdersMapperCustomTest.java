package cn.itcast.mybatis.mapper;

import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class OrdersMapperCustomTest {

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
    public void testfindOrderUser() throws Exception {

        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        OrdersMapperCustom ordersMapperCustom= session.getMapper(OrdersMapperCustom.class);


        //调用mapper的方法
        List<OrdersCustom> ordersCustomList =  ordersMapperCustom.findOrderUser();


        System.out.println(ordersCustomList);

        session.close();



    }


    @Test
    public void testfindOrdersUserResultMap() throws Exception{

        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        OrdersMapperCustom ordersMapperCustom= session.getMapper(OrdersMapperCustom.class);


        //调用mapper的方法
        List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();

        System.out.println(list);
        session.close();

    }


    @Test
    public void testFindOrdersAndOrderDetailResultMap() throws Exception{

        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        OrdersMapperCustom ordersMapperCustom= session.getMapper(OrdersMapperCustom.class);


        //调用mapper的方法
        List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailResultMap();
        System.out.println(list);

        session.close();

    }

    @Test
    public void testFindUserAndItemsResultMap() throws Exception{

        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        OrdersMapperCustom ordersMapperCustom= session.getMapper(OrdersMapperCustom.class);


        //调用mapper的方法
        List<User> list = ordersMapperCustom.findUserAndItemsResultMap();
        System.out.println(list);

        session.close();

    }



    @Test
    public void testFindOrdersUserLazyLoading() throws Exception{

        SqlSession session = sqlSessionFactory.openSession();

        //创建UserMapper对象，mybatis自动生成mapper代理对象
        OrdersMapperCustom ordersMapperCustom= session.getMapper(OrdersMapperCustom.class);


        //调用mapper的方法
        List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();

        //遍历列表
        for(Orders orders:list){
            //执行getUser（）去查询用户信息，这里实现按需加载
            User user = orders.getUser();
            System.out.println(user);
        }


        session.close();

    }


    @Test
    public void testCache1() throws Exception{
        SqlSession session = sqlSessionFactory.openSession();

        UserMapper userMapper = session.getMapper(UserMapper.class);


        //下边查询使用一个sqlsession,第一次发起请求，查询id为1的用户
        User user1 = userMapper.findUserById(1);
        System.out.println(user1);

        // 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

        // 更新user1的信息
        user1.setUsername("测试用户22");
        userMapper.updateUser(user1);
        // //执行commit操作去清空缓存
        session.commit();

        // 第二次发起请求，查询id为1的用户
        User user2 = userMapper.findUserById(1);
        System.out.println(user2);
        session.close();
    }

    //测试二级缓存
    @Test
    public void testCache2() throws Exception{
        SqlSession session1= sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        SqlSession session3 = sqlSessionFactory.openSession();

        UserMapper userMapper1 = session1.getMapper(UserMapper.class);
        //下边查询使用一个sqlsession,第一次发起请求，查询id为1的用户
        User user1 = userMapper1.findUserById(1);
        System.out.println(user1);
        //执行关闭，将sqlsession的数据写到二级缓存区域
        session1.close();

        UserMapper userMapper3 = session3.getMapper(UserMapper.class);
        User user3 = userMapper3.findUserById(1);
        user3.setUsername("张明明");
        userMapper3.updateUser(user3);

        //执行提交，清空usermapper下的二级缓存
        session3.commit();
        session3.close();




        UserMapper userMapper2 = session2.getMapper(UserMapper.class);

        // 第二次发起请求，查询id为1的用户
        User user2 = userMapper2.findUserById(1);
        System.out.println(user2);
        session2.close();



    }
}
