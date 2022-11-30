package kr.letech.study02.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kr.letech.study02.cmn.security.filter.CustomAuthenticationFilter;
import kr.letech.study02.cmn.security.handler.CustomLoginSuccessHandler;
import kr.letech.study02.cmn.security.provider.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;

	 // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations(), PathRequest.toH2Console());
        web.ignoring().antMatchers("/h2-console/*");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/h2-console/**").disable().authorizeRequests()
                // /about 요청에 대해서는 로그인을 요구함
                .antMatchers("/cmn/code").authenticated()
                // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
                .antMatchers("/cmn/code").hasRole("ADMIN")
                // 나머지 요청에 대해서는 로그인을 요구하지 않음
                .anyRequest().permitAll()
            .and()
                // 로그인하는 경우에 대해 설정함
            	.formLogin()
                // 로그인 페이지를 제공하는 URL을 설정함
                .loginPage("/loginForm.do")
                // 로그인 성공 URL을 설정함
                .successForwardUrl("/")
                // 로그인 실패 URL을 설정함
                .failureForwardUrl("/error/403")
                .permitAll()
            .and()
            	.logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/")
            	.invalidateHttpSession(true)
            .and()
               	.addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            ;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/loginProcess");
        customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        customAuthenticationFilter.afterPropertiesSet();
        return customAuthenticationFilter;
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(bCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }
}
