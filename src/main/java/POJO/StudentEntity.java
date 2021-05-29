package POJO;

import DAO.CRMuserDAO;

import javax.persistence.*;

@Entity
@Table(name = "student", schema = "public", catalog = "crmdata")
public class StudentEntity {
    private String studentid;
    private String studentname;
    private Boolean gender;
    private String classid;
    private String userid;


    @Id
    @Column(name = "studentid")
    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    @Basic
    @Column(name = "studentname")
    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    @Basic
    @Column(name = "gender")
    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "classid")
    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    @Basic
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentEntity that = (StudentEntity) o;

        if (studentid != null ? !studentid.equals(that.studentid) : that.studentid != null) return false;
        if (studentname != null ? !studentname.equals(that.studentname) : that.studentname != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (classid != null ? !classid.equals(that.classid) : that.classid != null) return false;
        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = studentid != null ? studentid.hashCode() : 0;
        result = 31 * result + (studentname != null ? studentname.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (classid != null ? classid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }
}
