package me.cxis.gof.observer_pattern.example3;

public class Event {

    private String key;

    private String value;

    private String eventType;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
            "key='" + key + '\'' +
            ", value='" + value + '\'' +
            ", eventType='" + eventType + '\'' +
            '}';
    }
}
