package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.dao.OauthDao;
import cn.edu.nefu.gdms.dto.OauthClientDetailsDto;
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
