package ci.palmafrique.vonabri.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
  	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Override
	public void configure(WebSecurity web) throws Exception{
		
		 web.ignoring().antMatchers("/user/login/**");
		 web.ignoring().antMatchers("/user/passwordForgotten/**");
//		 web.ignoring().antMatchers("/client/auth/**");
//		 web.ignoring().antMatchers("/resources/**");
//		 web.ignoring().antMatchers("/wallet/getByCriteria/**");
	}
		
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		}
		
//		@Bean
//		@Override
//		public AuthenticationManager authenticationManagerBean() throws Exception {
//			return super.authenticationManagerBean();
//		}
	
//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable()
//				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//				.authorizeRequests()
//				.antMatchers(HttpMethod.POST, "/user").permitAll()
//				.anyRequest().authenticated();
//		}
	
		@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.csrf().disable();
		
		//http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

		
		http.authorizeRequests().antMatchers("/user/login").permitAll();
		http.authorizeRequests().antMatchers("/logo_cip.png").permitAll();

		
//		http.authorizeRequests().antMatchers("/**/getByCriteria").permitAll();
//		http.authorizeRequests().antMatchers("/**/getStatistique").permitAll();
//		http.authorizeRequests().antMatchers("/**/resetPasscode").permitAll();
//		http.authorizeRequests().antMatchers("/**/becomeMarchandFromAdmin").permitAll();
//
//
//		http.authorizeRequests().antMatchers("/**/create").permitAll();
//		http.authorizeRequests().antMatchers("/**/update").permitAll();
//		http.authorizeRequests().antMatchers("/**/delete").permitAll();
//		http.authorizeRequests().antMatchers("/**/export/excel").permitAll();
//		http.authorizeRequests().antMatchers("/**/home").permitAll();
//		http.authorizeRequests().antMatchers("/**/push").permitAll();
//
//		
//		http.authorizeRequests().antMatchers("/**/transaction").permitAll();
//		http.authorizeRequests().antMatchers("/**/user/**").permitAll();
//		http.authorizeRequests().antMatchers("/demande/validated").permitAll();
//		http.authorizeRequests().antMatchers("/demande/decline").permitAll();
//		http.authorizeRequests().antMatchers("/wallet/generateqrcode").permitAll();
//		http.authorizeRequests().antMatchers("/wallet/generateqrcodeplus").permitAll();
//		http.authorizeRequests().antMatchers("/wallet/credite").permitAll();
//		http.authorizeRequests().antMatchers("/wallet/debite").permitAll();
//		http.authorizeRequests().antMatchers("/client/becomeMarchandFromAdmin").permitAll();
//		http.authorizeRequests().antMatchers("/client/createFromAdmin").permitAll();
//		http.authorizeRequests().antMatchers("/client/createSellerFromAdmin").permitAll();
//
//		http.authorizeRequests().antMatchers("/client/push").permitAll();

		
		
//		http.authorizeRequests().antMatchers("/client/getByCriteria/**").permitAll();

		
		
//		http.authorizeRequests().antMatchers("/client/auth/**").permitAll();

		


		
		http.authorizeRequests().anyRequest().authenticated();
		

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		//http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/user/login/**").permitAll().anyRequest().authenticated()
//		//if any exception occurs call this
////		.and().exceptionHandling()
////        .authenticationEntryPoint(unauthorizedHandler).and().
//		// make sure we use stateless session; session won't be used to
//		// store user's state.
//		.sessionManagement()
//		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//// 		Add a filter to validate the tokens with every request
//		http.addFilterBefore(new JWTAuthorizationFilter(), 
//		UsernamePasswordAuthenticationFilter.class);
//		
	}
}
