package br.cesed.si.pp.config.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.cesed.si.pp.model.enums.RoleUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AutenticacaoService implements UserDetails {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private String senha;
	private Set<? extends GrantedAuthority> authorities;

	public AutenticacaoService() {
	}

	public AutenticacaoService(Integer id, String email, String senha, Set<RoleUsuario> perfil) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.authorities = perfil.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toSet());
	}
	


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public boolean hasRole(RoleUsuario user) {
		return getAuthorities().contains(new SimpleGrantedAuthority(user.getDescricao()));
	}

}
