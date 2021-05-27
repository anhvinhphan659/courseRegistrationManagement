package POJO;

import javax.persistence.*;

@Entity
@Table(name = "courseopen", schema = "public", catalog = "crmdata")
public class CourseopenEntity {
    private String openid; //openid = subjectid+courseclass
    private String subjectid;
    private String courseclass;
    private Integer beginshift;
    private Integer endshift;
    private Integer diw;
    private String teacher;
    private Integer maxtotal;

    @Id
    @Column(name = "openid")
    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Basic
    @Column(name = "subjectid")
    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        openid=subjectid+courseclass;
    }

    @Basic
    @Column(name = "courseclass")
    public String getCourseclass() {
        return courseclass;
    }

    public void setCourseclass(String courseclass) {
        this.courseclass = courseclass;
        openid=subjectid+courseclass;
    }

    @Basic
    @Column(name = "beginshift")
    public Integer getBeginshift() {
        return beginshift;
    }

    public void setBeginshift(Integer beginshift) {
        this.beginshift = beginshift;
    }

    @Basic
    @Column(name = "endshift")
    public Integer getEndshift() {
        return endshift;
    }

    public void setEndshift(Integer endshift) {
        this.endshift = endshift;
    }

    @Basic
    @Column(name = "diw")
    public Integer getDiw() {
        return diw;
    }

    public void setDiw(Integer diw) {
        this.diw = diw;
    }

    @Basic
    @Column(name = "teacher")
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Basic
    @Column(name = "maxtotal")
    public Integer getMaxtotal() {
        return maxtotal;
    }

    public void setMaxtotal(Integer maxtotal) {
        this.maxtotal = maxtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseopenEntity that = (CourseopenEntity) o;

        if (openid != null ? !openid.equals(that.openid) : that.openid != null) return false;
        if (subjectid != null ? !subjectid.equals(that.subjectid) : that.subjectid != null) return false;
        if (courseclass != null ? !courseclass.equals(that.courseclass) : that.courseclass != null) return false;
        if (beginshift != null ? !beginshift.equals(that.beginshift) : that.beginshift != null) return false;
        if (endshift != null ? !endshift.equals(that.endshift) : that.endshift != null) return false;
        if (diw != null ? !diw.equals(that.diw) : that.diw != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        if (maxtotal != null ? !maxtotal.equals(that.maxtotal) : that.maxtotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = openid != null ? openid.hashCode() : 0;
        result = 31 * result + (subjectid != null ? subjectid.hashCode() : 0);
        result = 31 * result + (courseclass != null ? courseclass.hashCode() : 0);
        result = 31 * result + (beginshift != null ? beginshift.hashCode() : 0);
        result = 31 * result + (endshift != null ? endshift.hashCode() : 0);
        result = 31 * result + (diw != null ? diw.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (maxtotal != null ? maxtotal.hashCode() : 0);
        return result;
    }
}
