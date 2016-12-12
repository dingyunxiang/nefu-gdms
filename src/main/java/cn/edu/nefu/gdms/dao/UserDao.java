package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/5.
 */
public interface UserDao {

    long insert(UserPO userPO);

    void insertList(List<UserPO> list);

    int delete(long id);

    int update(UserPO userPO);

    UserPO get(long id);

    UserPO find(String username);

    List<UserPO> findByTutorId(long tutorId);

    List<UserPO> findByType(@Param("type") int type, @Param("offset") int offset, @Param("size") int size, @Param("username") String username, @Param("name") String name);
}
