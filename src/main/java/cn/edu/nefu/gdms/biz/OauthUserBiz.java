package cn.edu.nefu.gdms.biz;

import cn.edu.nefu.gdms.model.UserPO;
import cn.edu.nefu.gdms.oauth.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by dingyunxiang on 16/12/21.
 */
@Service
public class OauthUserBiz  implements UserDetailsService {
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private OauthBiz oauthBiz;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO user = userBiz.get(username);
        if (user == null ) {
            throw new UsernameNotFoundException("Not found any user for username[" + username + "]");
        }

        return new CustomUserDetails(user,oauthBiz);
    }

//    public UserJsonDto loadCurrentUserJsonDto() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        final Object principal = authentication.getPrincipal();
//
//        if (authentication instanceof OAuth2Authentication &&
//                (principal instanceof String || principal instanceof org.springframework.security.core.userdetails.User)) {
//            return loadOauthUserJsonDto((OAuth2Authentication) authentication);
//        } else {
//            final WdcyUserDetails userDetails = (WdcyUserDetails) principal;
//            return new UserJsonDto(userRepository.findByGuid(userDetails.user().guid()));
//        }
//    }
//
//    public UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto) {
//        List<User> users = userRepository.findUsersByUsername(overviewDto.getUsername());
//        overviewDto.setUserDtos(UserDto.toDtos(users));
//        return overviewDto;
//    }
//
//    public boolean isExistedUsername(String username) {
//        final User user = userRepository.findByUsername(username);
//        return user != null;
//    }
//
//    public String saveUser(UserFormDto formDto) {
//        User user = formDto.newUser();
//        userRepository.saveUser(user);
//        return user.guid();
//    }
//
//
//    private UserJsonDto loadOauthUserJsonDto(OAuth2Authentication oAuth2Authentication) {
//        UserJsonDto userJsonDto = new UserJsonDto();
//        userJsonDto.setUsername(oAuth2Authentication.getName());
//
//        final Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
//        for (GrantedAuthority authority : authorities) {
//            userJsonDto.getPrivileges().add(authority.getAuthority());
//        }
//
//        return userJsonDto;
//    }
}
