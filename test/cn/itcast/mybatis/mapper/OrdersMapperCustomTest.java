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
}
