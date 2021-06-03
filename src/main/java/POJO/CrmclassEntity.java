package POJO;

import javax.persistence.*;

@Entity
@Table(name = "crmclass", schema = "public", catalog = "crmdata")
public class CrmclassEntity {
    private String classid;
    private Integer male;
    private Integer female;
    private Integer yearstart;
    private Integer yearend;

    @Id
    @Column(name = "classid")
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "male")
    public Integer getMale() {
        return male;
    }

    public void setMale(Integer male) {
        this.male = male;
    }

    @Basic
    @Column(name = "female")
    public Integer getFemale() {
        return female;
    }

    public void setFemale(Integer female) {
        this.female = female;
    }

    @Basic
    @Column(name = "yearstart")
    public Integer getYearstart() {
        return yearstart;
    }

    public void setYearstart(Integer yearstart) {
        this.yearstart = yearstart;
    }

    @Basic
    @Column(name = "yearend")
    public Integer getYearend() {
        return yearend;
    }

    public void setYearend(Integer yearend) {
        this.yearend = yearend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrmclassEntity that = (CrmclassEntity) o;

        if (classid != null ? !classid.equals(that.classid) : that.classid != null) return false;
        if (male != null ? !male.equals(that.male) : that.male != null) return false;
        if (female != null ? !female.equals(that.female) : that.female != null) return false;
        if (yearstart != null ? !yearstart.equals(that.yearstart) : that.yearstart != null) return false;
        if (yearend != null ? !yearend.equals(that.yearend) : that.yearend != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "CrmclassEntity{" +
                "classid='" + classid + '\'' +
                ", male=" + male +
                ", female=" + female +
                ", yearstart=" + yearstart +
                ", yearend=" + yearend +
                '}';
    }

    @Override
    public int hashCode() {
        int result = classid != null ? classid.hashCode() : 0;
        result = 31 * result + (male != null ? male.hashCode() : 0);
        result = 31 * result + (female != null ? female.hashCode() : 0);
        result = 31 * result + (yearstart != null ? yearstart.hashCode() : 0);
        result = 31 * result + (yearend != null ? yearend.hashCode() : 0);
        return result;
    }

    public CrmclassEntity()
    {

    }
    public CrmclassEntity(Object[] data)
    {
        if(data.length==5)
        {
            classid=(String) data[0];
            male=Integer.valueOf((String) data[1]);
            female=Integer.valueOf((String) data[2]);
            yearstart=Integer.valueOf((String) data[3]);
            yearend=Integer.valueOf((String) data[4]);
        }
    }
}
