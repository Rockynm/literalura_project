package com.math.literalura.models;

public enum Generos {
    FILOSOFIA("Browsing: Philosophy & Ethics", "A"),
    PSICOLOGIA("Browsing: Psychiatry/Psychology", "B"),
    PARANORMAL("Browsing: Religion/Spirituality/Paranormal", "C"),
    CULTURA("Browsing: Culture/Civilization/Society", "D"),
    SEXUALIDAD("Browsing: Gender & Sexuality Studies", "E"),
    LITERATURA("Browsing: Literature", "F"),
    FANTASIA("Browsing: Science-Fiction & Fantasy", "G"),
    FICCIONGOTICA("Gothic Fiction", "H"),
    LIBROPELICULA("Movie Books", "I"),
    PREFICCION("Precursors of Science Fiction", "J"),
    WOMFICCION("Science Fiction by Women", "K"),
    NINOS("Browsing: Children & Young Adult Reading", "L"),
    FICCION("Browsing: Fiction", "L"),
    NAVIDAD("Christmas", "M"),
    NINOSLITERATURA("Children's Literature", "N"),
    MEJORESLIBROS("Best Books Ever Listings", "O"),
    HISTORIA("Browsing: History - General", "P");

    private String genero;
    private String generoEs;

    Generos(String genero, String generoEs){
        this.genero = genero;
        this.generoEs = generoEs;
    }




    public static Generos fromString(String text) {
        for (Generos categoria : Generos.values()) {
            if (categoria.genero.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public static Generos fromEspanol(String text) {
        for (Generos categoria : Generos.values()) {
            if (categoria.generoEs.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

}
