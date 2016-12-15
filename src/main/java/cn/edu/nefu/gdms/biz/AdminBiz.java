package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.common.ErrorCodeEnum;
import cn.edu.nefu.gdms.dao.UserDao;
import cn.edu.nefu.gdms.exception.ServiceException;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dingyunxiang on 16/12/13.
 */
@Service
public class AdminBiz {

    @Autowired
    private UserBiz userBiz;
    @Autowired
    private UserDao userDao;


    public void assignTutor(List<Long> tutorIds) {

    }

    public void assignTutor(List<Long> stuIds, List<Long> tutorIds) {
        List<Long> randomStuIds = getRandomList(stuIds);
        List<Long> randomTutorIds = getRandomList(tutorIds);
        List<UserPO> studentUserPOs = userBiz.getUserByIds(randomStuIds);
        List<UserPO> tutorUserPOs = userBiz.getUserByIds(randomTutorIds);
        int sum = 0;
        for (UserPO userPO : tutorUserPOs) {
            sum += userPO.getTitle();
        }
        if (sum < stuIds.size()) {
            throw new ServiceException(ErrorCodeEnum.TEA_NUM_NOT_ENOUGH);
        }
        int index = 0;
        int[] mutex = new int[tutorUserPOs.size()];
        for (int i = 0, j = 0; i < studentUserPOs.size(); i++, j++) {
            if (j >= tutorUserPOs.size()) {
                j = j % tutorUserPOs.size();
            }
            studentUserPOs.get(i).setTutorId(tutorUserPOs.get(j).getId());
        }
        userBiz.updateUserPOs(studentUserPOs);
    }


    private List<Long> getRandomList(List<Long> idList) {
        HashSet<Long> set = new HashSet<Long>(idList);
        List<Long> randomList = new ArrayList<Long>(set);
        return randomList;
    }
}
