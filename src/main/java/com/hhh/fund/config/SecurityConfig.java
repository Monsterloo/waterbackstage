package com.hhh.fund.config;

import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.hhh.security.ShiroDBRealm;
import com.hhh.security.ShiroKeyRealm;

/**
 * Shiro配置
 * @author 3hzl
 *
 */
@Configuration
public class SecurityConfig implements EnvironmentAware  {
	private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	private Environment env;
	
	public void setEnvironment(final Environment environment) {
        this.env = environment;
    }
	
	
	
	
	@Bean(name="securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realms());
		securityManager.setSessionManager(sessionManager());
		
		return securityManager;
	}
	
	protected SessionManager sessionManager() {
		return new ServletContainerSessionManager();
	}
	
	@Bean
	public AuthorizingRealm shiroDBRealm() {
		return new ShiroDBRealm();
	}
	
	@Bean
	public AuthorizingRealm shiroKeyRealm() {
		return new ShiroKeyRealm();
	}
	
	@Bean
	public Collection<Realm> realms() {
		List<Realm> realms = new LinkedList<Realm>();
		realms.add(shiroDBRealm());
		//realms.add(shiroKeyRealm());
		return realms;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean(name="shiroFilter")
	public Filter shiroFilter() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager());
		
		shiroFilterFactoryBean.setLoginUrl(env.getProperty("loginURL"));
		shiroFilterFactoryBean.setSuccessUrl(env.getProperty("successURL"));
		shiroFilterFactoryBean.setUnauthorizedUrl(env.getProperty("unauthorizedURL"));
		
		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("anon", new AnonymousFilter());
		filters.put("anthc", new FormAuthenticationFilter());
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setRedirectUrl("/admin/login");
		filters.put("logout", logoutFilter);
		shiroFilterFactoryBean.setFilters(filters);
		
		Map<String, String> filterChainDefMap = new LinkedHashMap<String, String>();
		filterChainDefMap.put("/admin/login", "anon");
		filterChainDefMap.put("/admin/keyToLogin", "anon");
		filterChainDefMap.put("/admin/logout", "logout");
		filterChainDefMap.put("/admin/**", "anthc");
		filterChainDefMap.put("/fund/**", "anthc");
		filterChainDefMap.put("/demo/**", "anthc");//演示用，正式的可以注销
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefMap);
		try {
			return (Filter)shiroFilterFactoryBean.getObject();
		} catch(Exception ex) {
			throw new BeanCreationException("shiroFilter", "FactoryBean throw exception on object creation", ex);
		}
	}
}
