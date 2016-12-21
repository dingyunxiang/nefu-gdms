package cn.edu.nefu.gdms.dao;

import cn.edu.nefu.gdms.model.oauth.OauthClientDetails;

import java.util.List;

/**
 * Created by dingyunxiang on 16/12/21.
 */
public interface OauthDao {
    OauthClientDetails findOauthClientDetails(String clientId);

    List<OauthClientDetails> findAllOauthClientDetails();

    void updateOauthClientDetailsArchive(String clientId, boolean archive);

    void saveOauthClientDetails(OauthClientDetails clientDetails);
}
