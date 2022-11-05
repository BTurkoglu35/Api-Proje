package pojos.RequesPojo;

public class RequesUnkownPojo {
    private RequesInDataPojo data;
    private RequesInSupportPojo support ;

    public RequesUnkownPojo(RequesInDataPojo data, RequesInSupportPojo support) {
        this.data = data;
        this.support = support;
    }

    public RequesUnkownPojo() {
    }

    public RequesInDataPojo getData() {
        return data;
    }

    public void setData(RequesInDataPojo data) {
        this.data = data;
    }

    public RequesInSupportPojo getSupport() {
        return support;
    }

    public void setSupport(RequesInSupportPojo support) {
        this.support = support;
    }

    @Override
    public String toString() {
        return "RequesUnkownPojo{" +
                "data=" + data +
                ", support=" + support +
                '}';
    }
}
