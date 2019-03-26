package br.senai.sc.domain.enums;

public enum TipoCliente {
	PESSOAFISICA (1, "Pessoa Física"),
	PESSOAJURIDICA (1, "Pessoa Jurídica");
	
	private Integer codigo;
	private String descricao;
	
	private TipoCliente() {
	}

	private TipoCliente(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer codigo) {
		for (TipoCliente tipo : TipoCliente.values()) {
			if (tipo.getCodigo() == codigo) {
				return tipo;
			}
		}
		return null;
	}
	
	
}
