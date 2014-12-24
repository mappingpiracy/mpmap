package models;

/**
 * Created by alex on 12/24/14.
 */
public class Country {
    private Integer id;
    private Integer cowId;
    private String name;
    private String abbreviation;

    public Country() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCowId() {
        return cowId;
    }

    public void setCowId(Integer cowId) {
        this.cowId = cowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
