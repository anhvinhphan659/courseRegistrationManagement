package POJO;

import DAO.SubjectDAO;

import javax.persistence.*;

@Entity
@Table(name = "subject", schema = "public", catalog = "crmdata")
public class SubjectEntity {
    private String subjectid;
    private String subjectname;
    private Integer credit;
    private String falculty;

    @Id
    @Column(name = "subjectid")
    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    @Basic
    @Column(name = "subjectname")
    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    @Basic
    @Column(name = "credit")
    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "falculty")
    public String getFalculty() {
        return falculty;
    }

    public void setFalculty(String falculty) {
        this.falculty = falculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubjectEntity that = (SubjectEntity) o;

        if (subjectid != null ? !subjectid.equals(that.subjectid) : that.subjectid != null) return false;
        if (subjectname != null ? !subjectname.equals(that.subjectname) : that.subjectname != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (falculty != null ? !falculty.equals(that.falculty) : that.falculty != null) return false;

        return true;
    }

    public SubjectEntity()
    {

    }

    public SubjectEntity(Object[] data)
    {
        if(data.length==4)
        {
            subjectid=(String) data[0];
            subjectname=(String) data[1];
            credit=new Integer((String) data[2]);
            falculty=(String) data[3];
        }
    }

    @Override
    public int hashCode() {
        int result = subjectid != null ? subjectid.hashCode() : 0;
        result = 31 * result + (subjectname != null ? subjectname.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (falculty != null ? falculty.hashCode() : 0);
        return result;
    }
}
