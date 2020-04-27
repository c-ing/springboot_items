package com.spring.demo.interceptor_demo;

        import com.spring.demo.interceptor_demo.interceptors.AuthorityInterceptor;
        import com.spring.demo.interceptor_demo.interceptors.LoginInterceptor;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: cdc
 * @Date: 2020/3/25 17:50
 * @Description:   和springmvc的webmvc拦截配置一样
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
        //registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(AuthorityInterceptor()).addPathPatterns("/**");
    }

    // 基于URL的拦截器
    @Bean
    public LoginInterceptor LoginInterceptor() {
        return new LoginInterceptor();
    }

    // 基于注解的拦截器
    @Bean
    public AuthorityInterceptor AuthorityInterceptor() {
        return new AuthorityInterceptor();
    }
}
