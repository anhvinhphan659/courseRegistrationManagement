package POJO;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "semester", schema = "public", catalog = "crmdata")
public class SemesterEntity {
    private String idsemester;//
    private Integer namesemester;
    private Integer yearsemester;
    private Date datebegin;
    private Date dateend;
    private Boolean iscurrentsemester;

    @Id
    @Column(name = "idsemester")
    public String getIdsemester() {
        return idsemester;
    }

    public void setIdsemester(String idsemester) {
        this.idsemester = idsemester;
    }

    @Basic
    @Column(name = "namesemester")
    public Integer getNamesemester() {
        return namesemester;
    }

    public void setNamesemester(Integer namesemester) {
        this.namesemester = namesemester;

    }

    @Basic
    @Column(name = "yearsemester")
    public Integer getYearsemester() {
        return yearsemester;
    }

    public void setYearsemester(Integer yearsemester) {
        this.yearsemester = yearsemester;
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

        SemesterEntity that = (SemesterEntity) o;

        if (idsemester != null ? !idsemester.equals(that.idsemester) : that.idsemester != null) return false;
        if (namesemester != null ? !namesemester.equals(that.namesemester) : that.namesemester != null) return false;
        if (yearsemester != null ? !yearsemester.equals(that.yearsemester) : that.yearsemester != null) return false;
        if (datebegin != null ? !datebegin.equals(that.datebegin) : that.datebegin != null) return false;
        if (dateend != null ? !dateend.equals(that.dateend) : that.dateend != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idsemester != null ? idsemester.hashCode() : 0;
        result = 31 * result + (namesemester != null ? namesemester.hashCode() : 0);
        result = 31 * result + (yearsemester != null ? yearsemester.hashCode() : 0);
        result = 31 * result + (datebegin != null ? datebegin.hashCode() : 0);
        result = 31 * result + (dateend != null ? dateend.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "iscurrentsemester")
    public Boolean getIscurrentsemester() {
        return iscurrentsemester;
    }

    public void setIscurrentsemester(Boolean iscurrentsemester) {
        this.iscurrentsemester = iscurrentsemester;
    }

    public SemesterEntity()
    {

    }
    public SemesterEntity(Object[] data) {
        if(data.length==5)
        {
            idsemester=(String)data[0];
            namesemester=Integer.valueOf((String) data[1]);
            yearsemester=Integer.valueOf((String) data[2]);
            datebegin=convertStringToDate((String) data[3]);
            dateend=convertStringToDate((String) data[4]);
        }
    }

    public static Date convertStringToDate(String date)
    {
        String[] dates=date.split("-");
        Date ret=new Date(1,1,1);
        ret.setDate(Integer.valueOf(dates[2])-1900);
        ret.setMonth(Integer.valueOf(dates[1])-1);
        ret.setYear(Integer.valueOf(dates[0]));
        return ret;
    }
}
