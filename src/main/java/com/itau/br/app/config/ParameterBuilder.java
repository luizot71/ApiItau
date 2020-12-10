package com.itau.br.app.config;

public class ParameterBuilder {

    private String name;
    private String description;
    private String value;

    public ParameterBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ParameterBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ParameterBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public Parameter build() {
        return new Parameter(name, description, value);
    }
}
