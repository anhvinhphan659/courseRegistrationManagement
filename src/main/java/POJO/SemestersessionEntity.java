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

    public SemestersessionEntity()
    {

    }
    public SemestersessionEntity(Object[]data)
    {
        if(data.length==4)
        {
            semsesid=(String)data[0];
            idsemester=(String) data[1];
            datebegin=convertStringToDate((String) data[2]);
            dateend=convertStringToDate((String) data[3]);

        }
    }

    public static Date convertStringToDate(String date)
    {
        String[] dates=date.split("/");
       Date ret=new Date(1,1,1);
        ret.setDate(Integer.valueOf(dates[0]));
        ret.setMonth(Integer.valueOf(dates[1])-1);
        ret.setYear(Integer.valueOf(dates[2])-1900);
        return ret;
    }
}
