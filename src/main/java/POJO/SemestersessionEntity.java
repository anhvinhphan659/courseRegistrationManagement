package POJO;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "semestersession", schema = "public", catalog = "crmdata")
public class SemestersessionEntity {
    private String semsesid;
    private String idsemester;
    private Date datebegin;
    private Date dateend;

    @Id
    @Column(name = "semsesid")
    public String getSemsesid() {
        return semsesid;
    }

    public void setSemsesid(String semsesid) {
        this.semsesid = semsesid;
    }

    @Basic
    @Column(name = "idsemester")
    public String getIdsemester() {
        return idsemester;
    }

    public void setIdsemester(String idsemester) {
        this.idsemester = idsemester;
    }

    @Basic
    @Column(name = "datebegin")
    public Date getDatebegin() {
        return datebegin;
    }

    public void setDatebegin(Date datebegin) {
        this.datebegin = datebegin;
    }

    @Basic
    @Column(name = "dateend")
    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SemestersessionEntity that = (SemestersessionEntity) o;
        return Objects.equals(semsesid, that.semsesid) && Objects.equals(idsemester, that.idsemester) && Objects.equals(datebegin, that.datebegin) && Objects.equals(dateend, that.dateend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semsesid, idsemester, datebegin, dateend);
    }
}
