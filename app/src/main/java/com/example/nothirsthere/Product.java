package com.example.nothirsthere;

public class Product {
    String email,d,can;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getCan() {
        return can;
    }

    public void setCan(String can) {
        this.can = can;
    }

    public Product(String email, String d, String can) {
        this.email = email;
        this.d = d;
        this.can = can;
    }
}
