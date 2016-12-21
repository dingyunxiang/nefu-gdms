package cn.edu.nefu.gdms.oauth;

/**
 * Created by dingyunxiang on 16/12/20.
 */

import cn.edu.nefu.gdms.biz.OauthBiz;
import cn.edu.nefu.gdms.model.ResourcePO;
import cn.edu.nefu.gdms.model.RolePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 自定义权限信息数据源
 */
@Component("CustomFilterInvocationSecurityMetadataSource")
public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private OauthBiz oauthBiz;

    /**
     * 根据用户访问的资源获取访问该资源所需要权限
     *
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation invocation = (FilterInvocation) o;
        //当前用户所访问的资源url
        String servletPath = invocation.getRequest().getServletPath();

        List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();

        //从数据库中查出该资源
        ResourcePO resourcePO = oauthBiz.getResourcePoByURL(servletPath);

        //如果数据库中不存在该资源，直接返回空权限列表，表示不需要权限
        if (resourcePO == null)
            return configAttributes;

        //访问该资源所需要的角色权限
        List<RolePO> roleEntities = oauthBiz.getRolesByResId(resourcePO.getId());

        //如果角色权限列表为空，那么返回一个自定义的forbid权限，表示禁止访问
        if (roleEntities == null || roleEntities.isEmpty()) {
            ConfigAttribute attribute = new SecurityConfig("forbid");
            configAttributes.add(attribute);
            return configAttributes;
        }

        //遍历，把角色权限加进角色权限列表中
        for (RolePO re : roleEntities) {
            ConfigAttribute attribute = new SecurityConfig(re.getName());
            configAttributes.add(attribute);
        }

        return configAttributes;
    }

    /**
     * 获取所有角色权限
     *
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //从数据库查出所有角色实体
        List<RolePO> roleEntities = oauthBiz.findAllRole();

        //所有角色权限列表
        List<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();

        if (roleEntities == null || roleEntities.isEmpty())
            return configAttributes;

        //遍历，把角色权限加进角色权限列表中
        for (RolePO re : roleEntities) {
            ConfigAttribute ca = new SecurityConfig(re.getName());
            configAttributes.add(ca);
        }

        return configAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}