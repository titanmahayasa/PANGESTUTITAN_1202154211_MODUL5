package com.example.pangestutitan.pangestutitan_1202154211_modul5;

/**
 * Created by Pangestu Titan on 3/25/2018.
 */

public class Todo {

    private long id;
    private String name;
    private String desc;
    private String prio;

    public Todo(){
    }

    public Todo(String name, String desc, String prio) {
        this.name = name;
        this.desc = desc;
        this.prio = prio;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }

}
