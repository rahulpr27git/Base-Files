package com.rp.sampleapp.pojo;

import com.rp.util.adapter.annotations.Unique;

public class DiffUtilData {

    @Unique
    private int id;

    private String name;

    public DiffUtilData(int id, String name) {
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
}