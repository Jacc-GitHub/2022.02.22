package br.com.letscode.cookbook.enums;

public enum TipoRendimento {
    PORCOES("Porções"),
    PRATOS("Pratos"),
    COPOS("Copos"),
    PEDACOS("Pedaços"),
    DOCE("Doce"),
    SALGADO("Salgado"),
    BEBIDA("Bebida");

    private final String tipoRendimento;

    TipoRendimento(String tipoRendimento){
        this.tipoRendimento = tipoRendimento;
    }

    public String gettipoRendimento() {
        return tipoRendimento;
    }


}
