package POJO;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "courseopen", schema = "public", catalog = "crmdata")
public class CourseopenEntity {
    private String openid;
    private String subjectid;
    private String courseclass;
    private Integer beginshift;
    private Integer endshift;
    private Integer diw;
    private String teacher;
    private Integer maxtotal;
    private String semsesid;

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
        this.subjectid = subjectid;
    }

    @Basic
    @Column(name = "courseclass")
    public String getCourseclass() {
        return courseclass;
    }

    public void setCourseclass(String courseclass) {
        this.courseclass = courseclass;
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

    @Basic
    @Column(name = "semsesid")
    public String getSemsesid() {
        return semsesid;
    }

    public void setSemsesid(String semsesid) {
        this.semsesid = semsesid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseopenEntity that = (CourseopenEntity) o;
        return Objects.equals(openid, that.openid) && Objects.equals(subjectid, that.subjectid) && Objects.equals(courseclass, that.courseclass) && Objects.equals(beginshift, that.beginshift) && Objects.equals(endshift, that.endshift) && Objects.equals(diw, that.diw) && Objects.equals(teacher, that.teacher) && Objects.equals(maxtotal, that.maxtotal) && Objects.equals(semsesid, that.semsesid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(openid, subjectid, courseclass, beginshift, endshift, diw, teacher, maxtotal, semsesid);
    }
}
