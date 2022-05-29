package com.cet4.config;


import com.cet4.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * security配置类
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserServiceImpl customUserService;

    /**
     * 拦截器
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/*.html", "/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService)
                //启动MD5加密
                .passwordEncoder(new PasswordEncoder() {
                    MD5Util md5Util = new MD5Util();
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return md5Util.encode((String) rawPassword);
                    }
                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return encodedPassword.equals(md5Util.encode((String) rawPassword));
                    }
                });
    }

    /**
     * 访问授权
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/index")        //自定义登录页面
                .loginProcessingUrl("/userLogin")   //随便写的一个接口地址，只要登录from一致即可
                .defaultSuccessUrl("/main") //登录成功则跳转
                .failureUrl("/index?error=true")
                .permitAll()
                .and().authorizeRequests().antMatchers("/","/userLogin").permitAll() //放行
                .antMatchers("/users","/user","/administrator").hasRole("ADMIN")
                .antMatchers("/info/personalInfo").hasAnyRole("ADMIN","TEACHER")
                .antMatchers("/main").hasAnyRole("ADMIN","TEACHER","STUDENT")
                .and().csrf().disable().logout().logoutUrl("/logout");
        http.exceptionHandling().accessDeniedPage("/unauth")     //修改403默认界面
            .and().rememberMe().rememberMeParameter("remember-me"); //记住我
    }
}