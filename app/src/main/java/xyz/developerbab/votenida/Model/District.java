package xyz.developerbab.votenida.Model;

public class District {

    private String districtid,districtname;
    public District() {
    }

    public District(String districtid, String districtname) {
        this.districtid = districtid;
        this.districtname = districtname;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getDistrictname() {
        return districtname;
    }

    public void setDistrictname(String districtname) {
        this.districtname = districtname;
    }
}
