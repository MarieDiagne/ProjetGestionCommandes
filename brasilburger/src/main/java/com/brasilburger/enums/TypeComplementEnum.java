package com.brasilburger.enums;

public enum TypeComplementEnum {
    BOISSON("BOISSON"),
    FRITES("FRITES");

    private final String value;

    TypeComplementEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeComplementEnum fromString(String text) {
        for (TypeComplementEnum type : TypeComplementEnum.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException(
                "Type de complément invalide : " + text + ". Valeurs acceptées : BOISSON, FRITES");
    }

    @Override
    public String toString() {
        return value;
    }
}