package com.github.type;

/**
 * Created by jianghang on 2018/4/30.
 */
public enum Enabled {
    disabled(0),
    enabled(1);

    private final int value;

    private Enabled(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
