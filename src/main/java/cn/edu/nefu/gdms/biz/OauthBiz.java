package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.ResourceDao;
import cn.edu.nefu.gdms.dao.RoleDao;
import cn.edu.nefu.gdms.model.ResourcePO;
import cn.edu.nefu.gdms.model.RolePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/20.
 */
@Service
public class OauthBiz {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private ResourceDao resourceDao;


    public List<RolePO> getRolesByUserId(long userId) {
        return roleDao.getRolesByUserId(userId);
    }

    public List<RolePO> getRolesByResId(long resId) {
        return resourceDao.getRolesByResId(resId);
    }

    public List<RolePO> findAllRole() {
        return roleDao.findAll();
    }

    public ResourcePO getResourcePoByURL(String url){
        return resourceDao.find(url);
    }
}
