package POJO;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "courseregist", schema = "public", catalog = "crmdata")
public class CourseregistEntity {
    private String idregister;//idregis=idstudent+openid
    private String idstudent;
    private String openid;
    private Date dateregist;

    @Id
    @Column(name = "idregister")
    public String getIdregister() {
        return idregister;
    }

    public void setIdregister(String idregister) {
        this.idregister = idregister;
    }

    @Basic
    @Column(name = "idstudent")
    public String getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(String idstudent) {
        this.idstudent = idstudent;
        idregister=idstudent+openid;
    }

    @Basic
    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
        idregister=idstudent+openid;
    }

    @Basic
    @Column(name = "dateregist")
    public Date getDateregist() {
        return dateregist;
    }

    public void setDateregist(Date dateregist) {
        this.dateregist = dateregist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseregistEntity that = (CourseregistEntity) o;

        if (idregister != null ? !idregister.equals(that.idregister) : that.idregister != null) return false;
        if (idstudent != null ? !idstudent.equals(that.idstudent) : that.idstudent != null) return false;
        if (openid != null ? !openid.equals(that.openid) : that.openid != null) return false;
        if (dateregist != null ? !dateregist.equals(that.dateregist) : that.dateregist != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idregister != null ? idregister.hashCode() : 0;
        result = 31 * result + (idstudent != null ? idstudent.hashCode() : 0);
        result = 31 * result + (openid != null ? openid.hashCode() : 0);
        result = 31 * result + (dateregist != null ? dateregist.hashCode() : 0);
        return result;
    }
}
