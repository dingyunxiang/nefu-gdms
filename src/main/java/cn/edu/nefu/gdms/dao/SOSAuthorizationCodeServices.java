package cn.edu.nefu.gdms.dao;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/**
 * 2016/7/23
 *
 * @author
 */
public class SOSAuthorizationCodeServices extends JdbcAuthorizationCodeServices {


    public SOSAuthorizationCodeServices(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    protected void store(String code, OAuth2Authentication authentication) {
        super.store(code, authentication);
    }

    @Override
    public OAuth2Authentication remove(String code) {
        return super.remove(code);
    }
}
