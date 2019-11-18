package br.cesed.si.pp.config.validacao;

public class ErroDeFormularioDto {

	private String campo; // campo do form
	private String erro;

	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
