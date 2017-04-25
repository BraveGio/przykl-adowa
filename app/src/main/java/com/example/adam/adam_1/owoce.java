package com.example.adam.adam_1;

/**
 * Created by Adam on 28.02.2017.
 */

public class owoce {
    private int id;
    private String litera;
    private byte[] zdjecie;


    public owoce(String litera, byte[] zdjecie, int id) {
        this.litera = litera;
        this.zdjecie = zdjecie;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLitera() {
        return litera;
    }

    public void setLitera(String litera) {
        this.litera = litera;
    }

    public byte[] getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(byte[] zdjecie) {
        this.zdjecie = zdjecie;
    }
}
