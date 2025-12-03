package fibrastar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                // Permitir archivos estáticos (CSS, JS) para que el login se vea bonito
                .requestMatchers("/css/**", "/js/**", "/img/**", "/webjars/**").permitAll()
                // Cualquier otra página requiere login
                .anyRequest().authenticated()
            )
            .formLogin((form) -> form
                .loginPage("/login") // Nuestra vista personalizada
                .defaultSuccessUrl("/dashboard", true) // Al entrar, ir al dashboard
                .permitAll()
            )
            .logout((logout) -> logout
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Usuario ADMIN con contraseña '123456'
        UserDetails user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("123456")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
}