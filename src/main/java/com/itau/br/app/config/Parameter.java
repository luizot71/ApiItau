package com.itau.br.app.config;

import springfox.documentation.builders.ParameterBuilder;

public class Parameter {

    private String name;
    private String description;
    private String value;

    public Parameter(String name, String description, String value) {
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public Parameter(String featureName) {
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public static ParameterBuilder builder() {
        return new ParameterBuilder();
    }
}
