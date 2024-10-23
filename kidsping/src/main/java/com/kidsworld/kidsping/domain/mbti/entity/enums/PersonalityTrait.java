package com.kidsworld.kidsping.domain.mbti.entity.enums;

import lombok.Getter;

@Getter
public enum PersonalityTrait {

    INTROVERSION("I"),
    EXTRAVERSION("E"),

    SENSING("S"),
    INTUITION("N"),

    THINKING("T"),
    FEELING("F"),

    JUDGING("J"),
    PERCEIVING("P");

    private final String type;

    PersonalityTrait(String type) {
        this.type = type;
    }
}