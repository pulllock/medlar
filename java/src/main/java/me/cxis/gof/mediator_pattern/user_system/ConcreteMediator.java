package me.cxis.gof.mediator_pattern.user_system;

public class ConcreteMediator extends Mediator {

    public Button addButton;

    public List list;

    public TextBox userTextBox;

    public ComboBox comboBox;

    @Override
    public void componentChanged(Component component) {
        if (component == addButton) {
            list.update();
            comboBox.update();
            userTextBox.update();
        } else if (component == list) {
            comboBox.select();
            userTextBox.update();
        } else if (component == comboBox) {
            comboBox.select();
            userTextBox.update();
        }
    }
}
