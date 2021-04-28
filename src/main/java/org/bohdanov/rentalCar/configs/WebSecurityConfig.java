package org.bohdanov.rentalCar.configs;

import org.bohdanov.rentalCar.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                //Доступ только для не зарегистрированных пользователей
//                    .antMatchers("/registration").not().fullyAuthenticated()
//                //Доступ только для пользователей с ролью Администратор
//                    .antMatchers("/admin/**").hasRole("ADMIN")
//                    .antMatchers("/a").hasRole("USER_BUYER")
//                    .antMatchers("/add-car", "/ad").hasRole("USER_RENT")
//                //Доступ разрешен всем пользователей
//                    .antMatchers("/","/resources/**", "/login").permitAll()
//                //Все остальные страницы требуют аутентификации
//                    .anyRequest().authenticated()
//                .and()
//                //Настройка для входа в систему
//                    .formLogin()
//                    .loginPage("/login")
//                //Перенарпавление на главную страницу после успешного входа
//                    .defaultSuccessUrl("/")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll()
//                    .logoutSuccessUrl("/");
//    }

//TODO swagger - without js and css...
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .addFilterBefore(corsFilter(), SessionManagementFilter.class)
                .csrf();
//                .disable()
//                .authorizeRequests()
//                    .antMatchers("/authenticate", "/", "/registration").permitAll()
//                    .antMatchers("/admin/**", "/swagger-ui.html").hasRole("ADMIN")
//                    .antMatchers("/add-car/**", "/delete-car/**").hasRole("USER_RENT")
//                    .anyRequest().authenticated()
//                .and()
//                    .exceptionHandling()
//                    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .and()
//                    .sessionManagement()
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("https://rental-car-ua.netlify.app")
////                .allowedHeaders("*", "Authorization")
////                .allowedMethods("*");
//        registry.addMapping("/**")
//                .allowedOrigins("https://rental-car-ua.netlify.app")
//                .allowedMethods("GET", "POST", "OPTIONS", "PUT", "DELETE")
//                .allowedHeaders("Content-Type", "X-Requested-With", "Authorization", "accept", "Origin", "Access-Control-Request-Method",
//                        "Access-Control-Request-Headers")
//                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
}
