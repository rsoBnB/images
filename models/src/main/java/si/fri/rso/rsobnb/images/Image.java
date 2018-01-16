package si.fri.rso.rsobnb.images;

import org.eclipse.persistence.annotations.UuidGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "images")
@NamedQueries(value =
        {
                @NamedQuery(name = "Image.getAll", query = "SELECT o FROM images o"),
                @NamedQuery(name = "Image.findByRealEstate", query = "SELECT o FROM images o WHERE o.realEstateId = " +
                        ":realEstateId")
        })
@UuidGenerator(name = "idGenerator")
public class Image {

    @Id
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String path;

    private String description;

    private Date submitted;

    @Column(name = "realestate_id")
    private String realEstateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public String getRealEstateId() {
        return realEstateId;
    }

    public void setRealEstateId(String realEstateId) {
        this.realEstateId = realEstateId;
    }

}