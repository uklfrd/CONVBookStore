package hkmu.comps380f.convbookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/user/view/**").hasAnyRole("ADMIN", "UNRGE", "USER")
                        .requestMatchers("/user/create").hasAnyRole("ADMIN", "UNRGE")
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/book/delete/**").hasRole("ADMIN")
                        .requestMatchers("/book/edit/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/view/**").permitAll()
                        .requestMatchers("/book/create").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/book/**").hasAnyRole("USER", "ADMIN", "UNREG")
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )
                .rememberMe(remember -> remember
                        .key("uniqueAndSecret")
                        .tokenValiditySeconds(86400)
                )
                .httpBasic(withDefaults());
        return http.build();
    }
}