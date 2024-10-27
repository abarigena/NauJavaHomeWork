package ru.danil.NauJava.configs;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Конфигурационный класс для настройки безопасности Spring Security.
 * Аннотация {@link EnableWebSecurity} включает поддержку веб-безопасности в приложении.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Создает бин для кодировщика паролей, использующего алгоритм BCrypt.
     *
     * @return экземпляр {@link BCryptPasswordEncoder}, который используется для шифрования паролей
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настраивает фильтр безопасности, определяющий, какие URL-адреса требуют аутентификации и какие доступны без нее.
     *
     * @param httpSecurity объект {@link HttpSecurity}, с помощью которого конфигурируется безопасность HTTP
     * @return настроенная цепочка фильтров безопасности
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth.
                        requestMatchers("/registration").permitAll()
                        .requestMatchers("/swagger-ui/index.html").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated())
                .formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

}
