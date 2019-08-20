package data.business;

import error.NotFoundLangException;

public enum Language {
    RU(1),
    ENG(2),
    UKR(3);


    private int languageId;

    Language(int languageId) {
        this.languageId = languageId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public static Language provideLanguageByCode(int code) {
        switch (code) {
            case 1:
                return RU;
            case 2:
                return ENG;
            case 3:
                return UKR;
            default:
                throw new NotFoundLangException("Can not find language with code " + code);
        }
    }
}
