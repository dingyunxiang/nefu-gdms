package cn.edu.nefu.gdms.oauth;

import cn.edu.nefu.gdms.biz.OauthBiz;
import cn.edu.nefu.gdms.model.RolePO;
import cn.edu.nefu.gdms.model.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by dingyunxiang on 16/12/20.
 */
public class CustomUserDetails implements UserDetails {
    private OauthBiz oauthBiz;

    private UserPO userPO;
    //用户所拥有的权限
    private Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public CustomUserDetails(UserPO userPO,OauthBiz oauthBiz) {
        this.userPO = userPO;
        this.oauthBiz = oauthBiz;
        for (RolePO r : oauthBiz.getRolesByUserId(userPO.getId())) {
            GrantedAuthority authority = new SimpleGrantedAuthority(r.getName());
            authorities.add(authority);
        }
    }

    public CustomUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPO.getPassword();
    }

    @Override
    public String getUsername() {
        return userPO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}