package pojos.GorestPojo;

import pojos.GorestPojo.GorestDataPojo;

public class GorestMetaPojo {
    private String meta;
    private GorestDataPojo data;

    public GorestMetaPojo() {
    }

    public GorestMetaPojo(String meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GorestMetaPojo{" +
                " meta= " + meta +
                " data= " + data ;
    }
}
