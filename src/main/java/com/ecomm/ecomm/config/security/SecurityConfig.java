import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/login", 
                    "/register", 
                    "/css/**", 
                    "/js/**", 
                    "/img/**",
                    "/403").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/client/**").hasRole("CLIENT")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler((request, response, authentication) -> {
                    String role = authentication.getAuthorities().stream()
                        .findFirst().get().getAuthority();
                    if (role.equals("ROLE_ADMIN")) {
                        response.sendRedirect("/admin");
                    } else if(role.equals("ROLE_CLIENT")) {
                        response.sendRedirect("/client");
                    } else {
                        response.sendRedirect("/");
                    }
                })
                .permitAll()
            )
            .exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/403")
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/?logout")
                .permitAll()
            );
        
        return http.build();
    }
}
