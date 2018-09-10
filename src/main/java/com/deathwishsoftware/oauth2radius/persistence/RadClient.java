package com.deathwishsoftware.oauth2radius.persistence;

import javax.persistence.*;

@Entity
@Table(name = "radclient")
public class RadClient {
    public RadClient() {}

    public RadClient(String inetAddress, String sharedSecret) {
        this.inetAddress = inetAddress;
        this.sharedSecret = sharedSecret;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String inetAddress;

    @Column
    private String sharedSecret;

    @Override
    public String toString() {
        return String.format(
                "RadClient{inetAddress=%s, sharedSecret=%s...}",
                inetAddress, sharedSecret.substring(0, 3)
        );
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(String inetAddress) {
        this.inetAddress = inetAddress;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }
}
