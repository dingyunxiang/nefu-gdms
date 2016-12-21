package cn.edu.nefu.gdms.model.oauth;

import cn.edu.nefu.gdms.model.UserPO;
import com.monkeyk.sos.domain.userPO.Privilege;
import com.monkeyk.sos.domain.userPO.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userPOdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Spring Security中的 UserDetails 实现
 *
 * @author Shengzhao Li
 */
public class OauthUserDetails implements UserDetails {

    private static final long serialVersionUID = 3957586021470480642L;

    protected static final String ROLE_PREFIX = "ROLE_";
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.USER.name());

    protected UserPO userPO;

    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

    public OauthUserDetails() {
    }

    public OauthUserDetails(UserPO userPO) {
        this.userPO = userPO;
        initialAuthorities();
    }

    private void initialAuthorities() {
        //Default, everyone have it
        this.grantedAuthorities.add(DEFAULT_USER_ROLE);
        //default userPO have all privileges
        if (userPO.defaultUser()) {
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.UNITY.name()));
            this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + Privilege.MOBILE.name()));
        } else {
            final List<Privilege> privileges = userPO.privileges();
            for (Privilege privilege : privileges) {
                this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + privilege.name()));
            }
        }
    }

    /**
     * Return authorities, more information see {@link #initialAuthorities()}
     *
     * @return Collection of GrantedAuthority
     */
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userPO.password();
    }

    @Override
    public String getUsername() {
        return userPO.userPOname();
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

    public User userPO() {
        return userPO;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{userPO=").append(userPO);
        sb.append('}');
        return sb.toString();
    }
}