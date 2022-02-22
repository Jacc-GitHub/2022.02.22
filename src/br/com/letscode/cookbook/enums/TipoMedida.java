package br.com.letscode.cookbook.enums;

public enum TipoMedida {
    UNIDADE("Unidade"),
    GRAMA("Grama"),
    KILO("Kg"),
    COLHER_DE_SOPA("Colher de Sopa"),
    COLHER_DE_CHA("Colher de Chá"),
    MILILITRO("ml"),
    XICARA("Xícara"),
    PITADA("Pitada"),
    POTE("Pote");

    private final String tipoMedida;

    TipoMedida(String tipoMedida){
        this.tipoMedida = tipoMedida;
    }

    public String gettipoMedida() {
        return tipoMedida;
    }
}
