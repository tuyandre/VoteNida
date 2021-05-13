package xyz.developerbab.votenida.Model;

public class Province
{ String id,province;

    public Province() {
    }

    public Province(String id, String province) {
        this.id = id;
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
