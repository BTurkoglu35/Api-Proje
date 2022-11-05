package pojos.RequesPojo;

public class RequesInSupportPojo {
    private String url;
    private String text;

    public RequesInSupportPojo(String url, String text) {
        this.url = url;
        this.text = text;
    }

    public RequesInSupportPojo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "RequesInSupportPojo{" +
                "url='" + url + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
