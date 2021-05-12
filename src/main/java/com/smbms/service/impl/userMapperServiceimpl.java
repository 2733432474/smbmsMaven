package com.smbms.service.impl;

import com.smbms.entity.Role;
import com.smbms.entity.User;
import com.smbms.mapper.RoleMapper;
import com.smbms.mapper.UserMapper;
import com.smbms.service.userMapperService;
import com.smbms.until.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class userMapperServiceimpl implements userMapperService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<User> getList() {
        return userMapper.getList();
    }

    @Override
    public User getByid(Integer id) {
        return userMapper.getByid(id);
    }

    @Override
    public User getByuserCodeAnduserPassword(String userCode, String userPassword) {
        return userMapper.login(userCode,userPassword);
    }

    @Override
    public User getBypasword(String oldPassword) {


        return userMapper.getByPassword(oldPassword);
    }

    @Override
    public Integer pswModify(String rnewpassword,Integer id) {
        return userMapper.Mpdifypwd(rnewpassword,id);
    }

    @Override
    public List<Role> getRoles() {
        return roleMapper.getroles();
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }
    @Override
    public User getByuserCode(String userCode) {
        return userMapper.getByUserCode(userCode);
    }
    @Override
    public Page PageSize(String UserName,
                         Integer RoleId,
                         Integer currPageNo,
                         Integer pageSize){
        System.out.println("UserName---------"+UserName);
        System.out.println("RoleId---------"+RoleId);
        Integer totalcount = userMapper.sumCount(UserName, RoleId);
        System.out.println("总条数"+totalcount);
        //得到总页数
        Integer totalpage=totalcount%pageSize==0?totalcount/pageSize:(totalcount/pageSize)+1;
        System.out.println("总页数"+totalpage);
        //得到当前页码
        if(currPageNo<=0){
            currPageNo=1;
        }else if(currPageNo>=totalpage){
            currPageNo=totalpage;
        }
        System.out.println("currPageNo---"+currPageNo);
        Integer beginPage=(currPageNo-1)*pageSize;
        if(beginPage<0){
            beginPage=0;
        }
        System.out.println(beginPage);
        //当前页数：
        List<Object> userList = userMapper.PageSize(UserName, RoleId, beginPage, pageSize);
        Page page = new Page(currPageNo,pageSize,totalcount,totalpage,userList);
        return page;
    }

    @Override
    public Integer delectUser(String userid) {
        return userMapper.delectUser(userid);
    }

    @Override
    public Integer modify(User user) {
        return userMapper.modify(user);
    }
}
