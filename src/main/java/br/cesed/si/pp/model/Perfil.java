package br.cesed.si.pp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import br.cesed.si.pp.model.enums.RoleUsuario;

@Entity
public class Perfil implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Enumerated(EnumType.STRING)
	private RoleUsuario role;

	public RoleUsuario getRole() {
		return role;
	}

	public void setRole(RoleUsuario role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getAuthority() {
		return this.role.name();
	}


}
