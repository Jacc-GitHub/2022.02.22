package br.com.letscode.cookbook.enums;

public enum Categoria {
    DOCE("Doce"),
    SALGADO("Salgado"),
    BEBIDA("Bebida");

    private final String categoria;

    Categoria(String categoria){
        this.categoria = categoria;
    }

    public String getcategoria() {
        return categoria;
    }

}