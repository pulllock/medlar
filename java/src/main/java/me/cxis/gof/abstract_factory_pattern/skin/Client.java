package me.cxis.gof.abstract_factory_pattern.skin;

public class Client {

    public static void main(String[] args) {
        SkinFactory factory = (SkinFactory) XmlUtil.getBean();
        factory.createButton().display();
        factory.createComboBox().display();
        factory.cteateTextField().display();
    }
}
