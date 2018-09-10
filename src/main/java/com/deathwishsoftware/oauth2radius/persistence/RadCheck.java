package com.deathwishsoftware.oauth2radius.persistence;

import javax.persistence.*;

@Entity
@Table(name = "radcheck")
public class RadCheck {
    public RadCheck() {}

    public RadCheck(Integer id, String username, String attribute, String op, String value) {
        this.id = id;
        this.username = username;
        this.attribute = attribute;
        this.op = op;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String attribute;

    @Column(columnDefinition = "char(2)")
    private String op;

    private String value;

    @Override
    public String toString() {
        return String.format(
                "RadCheck{id=%d, username=%s, attribute=%s, op=%s, value=%s}",
                id, username, attribute, op, value
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
