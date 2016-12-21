package cn.edu.nefu.gdms.model.oauth;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * 2016/7/26
 * <p/>
 * 扩展默认的 TokenStore, 增加对缓存的支持
 *
 * @author
 */
public class CustomJdbcTokenStore extends JdbcTokenStore {


    public CustomJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }


    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return super.readAccessToken(tokenValue);
    }


    public void removeAccessToken(String tokenValue) {
        super.removeAccessToken(tokenValue);
    }


    public OAuth2RefreshToken readRefreshToken(String token) {
        return super.readRefreshToken(token);
    }

    public void removeRefreshToken(String token) {
        super.removeRefreshToken(token);
    }


}
