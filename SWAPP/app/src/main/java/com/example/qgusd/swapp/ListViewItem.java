package com.example.qgusd.swapp;

/**
 * Created by qgusd on 2018-11-02.
 */

public class ListViewItem {
    private String namestr ;
    private String numberstr ;

    public void setTitle(String title) {
        namestr = title ;
    }
    public void setDesc(String desc) {
        numberstr = desc ;
    }

    public String getTitle() {
        return this.namestr ;
    }
    public String getDesc() {
        return this.numberstr ;
    }
}

