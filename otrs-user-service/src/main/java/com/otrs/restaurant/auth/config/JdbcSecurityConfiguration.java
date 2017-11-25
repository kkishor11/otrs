package com.otrs.restaurant.auth.config;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableGlobalAuthentication
public class JdbcSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter
{

	@Bean
	public UserDetailsService userDetailsService(JdbcTemplate jdbcTemplate)
	{
		RowMapper<User> userRowMapper = (ResultSet rs, int i) -> new User(
		        rs.getString("email"), rs.getString("password"), true,true,true,true,
		        AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN"));
		return username -> jdbcTemplate.queryForObject(
		        "SELECT * from user where email = ?", userRowMapper, username);
	}

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(this.userDetailsService);
	}

}
