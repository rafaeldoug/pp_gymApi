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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.cesed.si.pp.model.enums.RoleUsuario;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthEntryPointFilter entryPoint;
	
//	public static final String[] PUBLIC_MATCHERS = {
//			"/exercicios",
//			"/swagger-ui.html#/",
//			"/login",
//		};

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
				.and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/").permitAll()
//				.antMatchers("/login").permitAll()
				.antMatchers(HttpMethod.GET, "/alunos/**").hasAnyAuthority(RoleUsuario.ADMIN.getDescricao())
				.antMatchers(HttpMethod.GET, "/exercicios").permitAll()
				.antMatchers(HttpMethod.POST, "/auth").permitAll()
				.antMatchers(HttpMethod.POST, "/professores").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(entryPoint).and()
				.addFilterBefore(new AutenticacaoViaTokenFilter(authenticationManager(), tokenService), UsernamePasswordAuthenticationFilter.class)
				.addFilter(new AutorizacaoViaTokenFilter(authenticationManager(), tokenService, userDetailsService))
				.addFilter(new AutenticacaoViaTokenFilter(authenticationManager(), tokenService))
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}


	// configurar recursos estaticos (js, css, imagens etc)
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", 
				"/v2/api-docs", 
				"/webjars/**", 
				"/configuration/**",
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

//	 gerador de senha encodada
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder().encode("admin"));
//	}
}
