package edu.fra.uas.model;

public class ResponseFile {
    private Long id;
//    private String url;
    private String type;

    public ResponseFile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

/*    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
