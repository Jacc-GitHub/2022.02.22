package br.com.letscode.cookbook.enums;

public enum ModoPreparo {
    ASSAR("Assar"),
    COZINHAR("Cozinhar"),
    MEXER("Mexer");

    private final String modoDePreparo;

    ModoPreparo(String modoDePreparo){
        this.modoDePreparo = modoDePreparo;
    }

    public String getmodoDePreparo() {
        return modoDePreparo;
    }

}
