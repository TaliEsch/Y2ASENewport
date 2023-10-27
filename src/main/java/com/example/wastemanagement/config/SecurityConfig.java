package com.example.wastemanagement.config;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListenerFactory;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@ComponentScan
public class SecurityConfig{

    @Autowired
    private Environment environment;




    // This retrieves the passwords and authorities based on the username and gives them jdbc authentication for the login.
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource(environment))
                .usersByUsernameQuery("select username, password, enabled where username=?")
                .authoritiesByUsernameQuery("select username, authority from authorities where username=?");
    }

    // This builds the database as the type H2.
    @Bean
    public static DataSource dataSource(Environment environment) {
        if (environment.getActiveProfiles()[0].equals("prod")){
            return new DriverManagerDataSource("jdbc:mariadb://localhost:3306/WasteManagement",
                    environment.getProperty("spring.datasource.username"),
                    environment.getProperty("spring.datasource.password"));
        }else {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }
    }


    UserDetailsService userDetailsService;


    /* UserDetailsService will handle the user accounts, accounts are currently hardcoded with a username,
     * bcrypt encrypted password and roles. Currently, only the USER role is in use but this can easily be changed
     * later on. The account details are then returned to the in memory user manager. This stores all the accounts
     * in memory, though this should be changed later to use JDBC */
    @Bean
    public UserDetailsService userDetailsService() {
        /* This will later need to import from a database */

        UserDetails user1 = User.builder()
                .username("Oliver")
                .password("$2a$10$dj1jfyIgv1glVfUs2eyete9s2r.vhGJTgSn3zLI7S/P94g8TWlFze")
                // 01
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.withUsername("Pushpa")
                .password("$2a$10$WofUIZcbBlMzEDBhAqyzcujGlVpGu98y5BzkIgLNiGUUVze.Y0Okq")
                // Secret password
                .roles("ADMIN")
                .build();
        UserDetails user3 = User.withUsername("Daniel")
                .password("$2a$10$rjANv69tqQ2LRRgnRxbENOm/D7nhhw7qa4qvh.V2feMakgIM9AI1G")
                //02
                .roles("ADMIN")
                .build();
        UserDetails user4 = User.withUsername("Ayman")
                .password("$2a$10$5GTmZ94VZldKUiP8clks4.xtLbbup4DlTUiRdY9MInTtn7YEOqvaK")
                // password1234
                .roles("ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource(environment));
        users.createUser(user1);
        users.createUser(user2);
        users.createUser(user3);
        users.createUser(user4);
        return users;
    }

    /* This is a manual hasher implementation I added to manually hash the passwords using BCRYPT, as passwords
     * have to be entered manually to the above userDetailsManager, this was used to generate some temporary test
     * accounts. This needs to be later automated to encrypt new users passwords entered to a user creation form */
    @Bean
    public static PasswordEncoder manualPasswordHasher() {
        // Needs to be deleted/replaced at some point
        return new BCryptPasswordEncoder();

    }

    /* The following is the main security code, that configures spring security */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(form -> form
                        .loginPage("/admin/login")
                        .permitAll()
                        .defaultSuccessUrl("/admin/home", true)
                        .failureUrl("/")
                        .usernameParameter("username")
                        .passwordParameter("password")
                )
                .authorizeRequests((authorizeRequests) ->
                                authorizeRequests
//                                .antMatchers("/councilInput/**").hasRole("USER")
                                        .antMatchers("/account-create").hasAuthority("ROLE_ADMIN")
                                        .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                        .antMatchers("/h2-console/**").permitAll()
                                        .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                        .antMatchers("/").permitAll()

                )
                .csrf(csrf ->
                        csrf
                                .ignoringAntMatchers("/h2-console/**")
                )
                .headers(headers->
                        headers
                                .frameOptions().disable()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/")
                )
                .httpBasic(withDefaults());

        return http.build();
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessManager();
    }

}




