package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.RolePO;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public interface RoleDao {
    public int insert(RolePO rolePO);

    public int update(RolePO rolePO);

    public int delete(long id);

    public RolePO get(long id);

    public List<RolePO> findAll();

    public List<RolePO> getRolesByUserId(long userId);
}
