package com.finewine.core.model.country;

public class Country {
    private Long id;
    private String russianName;
    private String englishName;
    private String shortCountry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRussianName() {
        return russianName;
    }

    public void setRussianName(String russianName) {
        this.russianName = russianName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getShortCountry() {
        return shortCountry;
    }

    public void setShortCountry(String shortCountry) {
        this.shortCountry = shortCountry;
    }
}
