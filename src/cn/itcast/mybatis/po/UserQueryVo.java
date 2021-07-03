package cn.itcast.mybatis.po;

public class UserQueryVo {
    //Description:包装类型  查询


    //用户查询条件

    private UserCustom userCustom;

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }
}
