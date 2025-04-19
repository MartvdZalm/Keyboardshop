package com.keyboardshop.enums;

import java.lang.IllegalArgumentException;

public enum Language
{
    NL("nl"),
    EN("en");

    private String code;

    Language(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public static Language fromCode(String code)
    {
        for (Language language : Language.values()) {
            if (language.getCode().equals(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("Invalid language code: " + code);
    }
}
