package ma.enset.patientsmvc.security;

import ma.enset.patientsmvc.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


           /*
          String encodedPWD=passwordEncoder.encode("1234");
          System.out.println(encodedPWD);
          auth.inMemoryAuthentication().withUser("user1").password(encodedPWD).roles("USER"); // {noop} lorsque l'on veux pas utilisé un encodeur de password(hachage)
          auth.inMemoryAuthentication().withUser("user2").password(encodedPWD).roles("USER");
          auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123456")).roles("USER","ADMIN");

         */
        /*
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username as principal,password as credentials,active from users where username=?")
                .authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username = ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);

        */
        auth.userDetailsService(userDetailsService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeHttpRequests().antMatchers("/").permitAll();
        http.authorizeHttpRequests().antMatchers("/admin/**").hasAuthority("ADMIN");
        http.authorizeHttpRequests().antMatchers("/user/**").hasAuthority("USER");
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        http.authorizeHttpRequests().anyRequest().authenticated();// ne pas oublié
        http.exceptionHandling().accessDeniedPage("/403");
    }

}
