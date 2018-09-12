package me.cxis.gof.prototype_pattern.official_document;

public class SRS implements OfficialDocument {

    @Override
    public OfficialDocument clone() {
        try {
            return (OfficialDocument)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void display() {

    }
}
