package me.cxis.gof.abstract_factory_pattern.skin;

public interface SkinFactory {

    Button createButton();

    TextField cteateTextField();

    ComboBox createComboBox();
}
