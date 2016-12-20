package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.ResourcePO;
import cn.edu.nefu.gdms.model.RolePO;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/20.
 */
public interface ResourceDao {
    public int insert(ResourcePO resourcePO);

    public int update(ResourcePO resourcePO);

    public int delete(long id);

    public ResourcePO get(long id);

    public ResourcePO find(String url);

    public List<RolePO> getRolesByResId(long resourceId);
}
