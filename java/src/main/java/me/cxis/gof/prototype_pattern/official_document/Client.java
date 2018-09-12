package me.cxis.gof.prototype_pattern.official_document;

public class Client {

    public static void main(String[] args) {
        PrototypeManager manager = PrototypeManager.getInstance();
        OfficialDocument document1 = manager.getOfficialDocument("far");
        document1.display();

        OfficialDocument document2 = manager.getOfficialDocument("far");
        document2.display();

        System.out.println(document1 == document2);

        OfficialDocument document3 = manager.getOfficialDocument("srs");
        document3.display();

        OfficialDocument document4 = manager.getOfficialDocument("srs");
        document4.display();

        System.out.println(document3 == document4);
    }
}
