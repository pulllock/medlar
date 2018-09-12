package me.cxis.gof.prototype_pattern.official_document;

import java.util.Hashtable;

public class PrototypeManager {

    private Hashtable hashtable = new Hashtable();

    private static PrototypeManager manager = new PrototypeManager();

    private PrototypeManager() {
        hashtable.put("far", new FAR());
        hashtable.put("srs", new SRS());
    }

    public static PrototypeManager getInstance() {
        return manager;
    }

    public void addOfficialDocument(String key, OfficialDocument document) {
        hashtable.put(key, document);
    }

    public OfficialDocument getOfficialDocument(String key) {
        return ((OfficialDocument)hashtable.get(key)).clone();
    }
}
