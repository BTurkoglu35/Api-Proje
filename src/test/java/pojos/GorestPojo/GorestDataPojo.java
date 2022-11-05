package pojos.GorestPojo;

public class GorestDataPojo {
    private Integer id;
    private String name;
    private String Email;
    private String gender;
    private String status;

    public GorestDataPojo() {
    }

    public GorestDataPojo(Integer id, String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        Email = email;
        this.gender = gender;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GorestDataPojo{" +
                " id=" + id +
                " name= " + name +
                " Email= " + Email +
                " gender= " + gender +
                " status= " + status ;
    }
}
