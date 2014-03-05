package eu.solidcraft.starter.conf.security
import com.google.common.annotations.VisibleForTesting
import eu.solidcraft.starter.conf.Profiles
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@Profile([Profiles.DEFAULT, Profiles.DEVELOPMENT, Profiles.TEST])
class HttpBasicSecurityConfig extends WebSecurityConfigurerAdapter {
    @VisibleForTesting public static final String USERNAME = "test"
    @VisibleForTesting public static final String PASSWORD = "test"

    @Autowired
    protected void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.
            inMemoryAuthentication().
                withUser(USERNAME).
                password(PASSWORD).
                roles("ADMIN")
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
            authorizeRequests().
                 anyRequest().authenticated().
                 and().
//            formLogin().permitAll().
//                and().
            httpBasic().
                and().
            csrf().disable()
    }

    @Override
    @Bean(name="authenticationManager")
    AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean()
    }
}
