package me.cxis.set.treeset;

/**
 * Created by cheng.xi on 2017-04-28 10:22.
 */
public class People implements Comparable<People>{
    private int id;
    private String name;

    People(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(People people) {
        return id > people.id ? 1 : (id == people.id ? 0 : -1);
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
