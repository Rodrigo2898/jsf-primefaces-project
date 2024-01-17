package com.jsfproject.jsfproject.entity.enums;

public enum TipoEmpresa {
    MEI("Microempreendedor Individual"),
    EIRELI("Empresa individual de Responsabilidade Limitada"),
    LTDA("Sociedade Limitada"),
    SA("Sociedade Anônima");

    private String descricao;

    TipoEmpresa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
