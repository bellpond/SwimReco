package com.swimreco.api.domain;

public enum Style {
    FreeStyle("Fr"),
    Breaststroke("Br"),
    Backstroke("Ba"),
    Butterflystroke("Fly"),
    IndividualMedley("IM");

    private String abbreviation;
    private Style(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
