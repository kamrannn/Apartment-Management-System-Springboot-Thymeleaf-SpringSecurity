package edu.fra.uas.model;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class WohnungPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Lob
    private byte[] picture;

    public WohnungPicture() {
    }

    public WohnungPicture(Long id, String type, byte[] picture) {
        this.id = id;
        this.type = type;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
