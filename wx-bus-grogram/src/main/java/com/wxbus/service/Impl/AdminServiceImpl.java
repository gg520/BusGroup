package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wxbus.dao.AdminMapper;
import com.wxbus.daomain.Admin;
import com.wxbus.daomain.AdminExample;
import com.wxbus.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/5/31
 * @time: 20:51
 * Description:
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final Log log= LogFactory.getLog(AdminServiceImpl.class.getName());
    @Resource
    private AdminMapper adminMapper;
    @Override
    /**
     *@type method
     *@parameter  [username, password]
     *@back  java.lang.String
     *@author  如花
     *@creattime 2018/5/31
     *@describe 管理员登陆
     */
    public String  findUserByNamePaw(String username, String password) {
        AdminExample adminExample=new AdminExample();
        adminExample.or().andAdminIdEqualTo(username).andAdminPasswordEqualTo(password);
        List<Admin> adminList=adminMapper.selectByExample(adminExample);
        if (adminList==null||adminList.size()==0){
            log.info("登陆失败");
            return null;
        }
        else {
            log.info("登陆成功");
            return new String("登陆成功");
        }




    }

    @Override
    /**
     *@type method
     *@parameter  [admin]
     *@back  void
     *@author  如花
     *@creattime 2018/6/13
     *@describe 添加管理员
     */
    public void addAdmin(Admin admin) {
        log.info("添加管理员");
        adminMapper.insertSelective(admin);
    }

    @Override
    /**
     *@type method
     *@parameter  []
     *@back  java.util.List<com.wxbus.daomain.Admin>
     *@author  如花
     *@creattime 2018/6/13
     *@describe 查找所有管理员
     */
    public List<Admin> findAllAdmin(Integer startNum,Integer num) {
        log.info("查找所有管理员");
        PageHelper.startPage(startNum,num);
        AdminExample adminExample=new AdminExample();

        return adminMapper.selectByExample(adminExample);
    }
}