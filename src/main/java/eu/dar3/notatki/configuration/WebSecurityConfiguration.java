package eu.dar3.notatki.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .httpBasic(Customizer.withDefaults())//BasicAuthenticationFilter

                .authorizeHttpRequests((requests) -> {
                            requests
                                    .requestMatchers(HttpMethod.GET, "/list", "/actuator/**").permitAll()
                                    .anyRequest()
                                    .authenticated();
                        }
                )

                .formLogin(AbstractAuthenticationFilterConfigurer::permitAll)
                .logout(LogoutConfigurer::permitAll);
//        System.out.println("Password \"user\": = " + new BCryptPasswordEncoder().encode("user"));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
