package com.mysong.api.util;

public class JwtBody {

    private String sub;
    private Integer exp;

    public JwtBody() {
    }

    public JwtBody(String sub, Integer exp) {
        this.sub = sub;
        this.exp = exp;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }
}
