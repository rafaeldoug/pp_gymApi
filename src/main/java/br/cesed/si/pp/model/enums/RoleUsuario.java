package br.cesed.si.pp.model.enums;

public enum RoleUsuario {

	PADRAO(1, "ROLE_PADRAO"),
	ADMIN(2, "ROLE_ADMIN"), 
	PROFESSOR(3, "ROLE_PROFESSOR") ;

	
	private int cod;
	private String descricao;

	private RoleUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static RoleUsuario toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (RoleUsuario x : RoleUsuario.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id Invalido: " + cod);
	}
}
