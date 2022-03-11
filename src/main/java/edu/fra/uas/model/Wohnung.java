package edu.fra.uas.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wohnung")
public class Wohnung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meter;
    private String price;
    private Boolean status;
    private String date;

    @OneToMany(targetEntity = WohnungPicture.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wohnung_id", referencedColumnName = "id")
    private List<WohnungPicture> wonhungPictures = new ArrayList<>();


    public Wohnung() {
    }

    public Wohnung(String meter, String price, Boolean status, String date) {
        this.meter = meter;
        this.price = price;
        this.status = status;
        this.date = date;
    }

    public Wohnung(Long id, String meter, String price, Boolean status, String date, List<WohnungPicture> wonhungPictures) {
        this.id = id;
        this.meter = meter;
        this.price = price;
        this.status = status;
        this.date = date;
        this.wonhungPictures = wonhungPictures;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeter() {
        return meter;
    }

    public void setMeter(String meter) {
        this.meter = meter;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<WohnungPicture> getWonhungPictures() {
        return wonhungPictures;
    }

    public void setWonhungPictures(List<WohnungPicture> tutorCreatedClasses) {
        this.wonhungPictures = tutorCreatedClasses;
    }
}
