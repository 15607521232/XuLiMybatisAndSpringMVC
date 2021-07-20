package cn.itcast.mybatis.mapper;


import cn.itcast.mybatis.po.Orders;
import cn.itcast.mybatis.po.OrdersCustom;
import cn.itcast.mybatis.po.User;

import java.util.List;

public interface OrdersMapperCustom {

    //查询订单关联查询用户信息
    public List<OrdersCustom> findOrderUser() throws Exception;

    //查询订单关联查询用户使用resultMap
    public List<Orders> findOrdersUserResultMap() throws Exception;

    //查询订单（关联用户）及订单明细
    public List<Orders> findOrdersAndOrderDetailResultMap() throws Exception;

    //查询用户购买商品信息
    public  List<User> findUserAndItemsResultMap() throws  Exception;
}
