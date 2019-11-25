package br.cesed.si.pp.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// configurar autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}

	// configurar autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().configurationSource(configurationSource())
				.and().csrf().disable().authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers(HttpMethod.GET, "/exercicios").permitAll()
				.antMatchers(HttpMethod.POST, "/professores").permitAll()
				.anyRequest().authenticated()
				.and().addFilter(new AutenticacaoViaTokenFilter(authenticationManager(), tokenService))
				.addFilter(new AutorizacaoViaTokenFilter(authenticationManager(), tokenService, userDetailsService))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

//	// configurar autorizacao
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//		.antMatchers(HttpMethod.GET, "/**").permitAll()
//		.antMatchers(HttpMethod.POST, "/**").permitAll()
//		.antMatchers(HttpMethod.PUT, "/**").permitAll()
//		.antMatchers(HttpMethod.DELETE, "/**").permitAll()
//		.antMatchers(HttpMethod.PATCH, "/**").permitAll()
////		.antMatchers(HttpMethod.POST, "/alunos").permitAll()
////		.antMatchers(HttpMethod.POST, "/treinos").permitAll()
//		.anyRequest().authenticated()
//		.and().csrf().disable()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
//	}

	// configurar recursos estaticos (js, css, imagens etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**",
				"/swagger-resources/**");
	}

	@Bean
	CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("*");
		configuration.addAllowedOrigin("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("passtest"));
//	}
}
