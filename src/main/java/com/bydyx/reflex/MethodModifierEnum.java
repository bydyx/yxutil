package com.bydyx.reflex;

public enum MethodModifierEnum {
    PUBLIC(1),
    PRIVATE(2),
    PROTECTED(4),
    STATIC(8),
    FINAL(16);
    int code;

    MethodModifierEnum(int code) {
        this.code = code;
    }

    public static boolean hasModifier(int code, MethodModifierEnum... modifiers) {
        for (MethodModifierEnum modifier : modifiers) {
            int i = modifier.code & code;
            if (i == modifier.code) {
                return true;
            }
        }
        return false;
    }
}