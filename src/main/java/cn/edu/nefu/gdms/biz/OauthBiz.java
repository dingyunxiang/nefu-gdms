package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.OauthDao;
import cn.edu.nefu.gdms.dao.ResourceDao;
import cn.edu.nefu.gdms.dao.RoleDao;
import cn.edu.nefu.gdms.dto.OauthClientDetailsDto;
import cn.edu.nefu.gdms.model.ResourcePO;
import cn.edu.nefu.gdms.model.RolePO;
import cn.edu.nefu.gdms.model.oauth.OauthClientDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/20.
 */
@Service
public class OauthBiz {
    @Autowired
    private OauthDao oauthDao;
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

    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthDao.findOauthClientDetails(clientId);
    }

    public List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos() {
        List<OauthClientDetails> clientDetailses = oauthDao.findAllOauthClientDetails();
        return OauthClientDetailsDto.toDtos(clientDetailses);
    }

    public void archiveOauthClientDetails(String clientId) {
        oauthDao.updateOauthClientDetailsArchive(clientId, true);
    }

    public OauthClientDetailsDto loadOauthClientDetailsDto(String clientId) {
        final OauthClientDetails oauthClientDetails = oauthDao.findOauthClientDetails(clientId);
        return oauthClientDetails != null ? new OauthClientDetailsDto(oauthClientDetails) : null;
    }

    public void registerClientDetails(OauthClientDetailsDto formDto) {
        OauthClientDetails clientDetails = formDto.createDomain();
        oauthDao.saveOauthClientDetails(clientDetails);
    }
}
