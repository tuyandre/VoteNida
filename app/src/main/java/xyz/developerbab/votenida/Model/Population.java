package xyz.developerbab.votenida.Model;

public class Population {
    String name;
    String nid;
    String phone;
    String sex;
    String dob;
    String province;
    String district;
    String biometric;

    public Population() {
    }


    public Population(String name, String nid, String phone, String sex, String dob, String province, String district, String biometric) {
        this.name = name;
        this.nid = nid;
        this.phone = phone;
        this.sex = sex;
        this.dob = dob;
        this.province = province;
        this.district = district;
        this.biometric = biometric;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getBiometric() {
        return biometric;
    }

    public void setBiometric(String biometric) {
        this.biometric = biometric;
    }
}
