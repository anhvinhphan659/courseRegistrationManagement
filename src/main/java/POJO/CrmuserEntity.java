package POJO;

import javax.persistence.*;

@Entity
@Table(name = "crmuser", schema = "public", catalog = "crmdata")
public class CrmuserEntity {
    private String userid;
    private String account;
    private String pass;
    private Boolean isadmin;

    @Id
    @Column(name = "userid")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Basic
    @Column(name = "isadmin")
    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CrmuserEntity that = (CrmuserEntity) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (pass != null ? !pass.equals(that.pass) : that.pass != null) return false;
        if (isadmin != null ? !isadmin.equals(that.isadmin) : that.isadmin != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        result = 31 * result + (isadmin != null ? isadmin.hashCode() : 0);
        return result;
    }

    public CrmuserEntity(String userid) {
        this.userid = userid;
    }

    public CrmuserEntity ()
    {

    }

    public CrmuserEntity(Object[] data)
    {
        if(data.length==4)
        {
            userid=(String) data[0];
            account=(String)data[1];
            pass=(String) data[2];
            isadmin=(Boolean) data[3];
        }
    }
    @Override
    public String toString() {
        return "CrmuserEntity{" +
                "userid='" + userid + '\'' +
                ", account='" + account + '\'' +
                ", pass='" + pass + '\'' +
                ", isadmin=" + isadmin +
                '}';
    }
}
