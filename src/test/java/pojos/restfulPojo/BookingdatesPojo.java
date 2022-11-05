package pojos.restfulPojo;

public class BookingdatesPojo {
  // tum keyler icin private variablelar olusturuyoruz
    private String checkin;
    private String checkout;

    //Tum parametreler ile paramaetreli ve paremetresiz constructor olusturuyoruz
    public BookingdatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public BookingdatesPojo() {
    }

    //public getter ve setter methodlarini olusturuyoruz.

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //toString() methodunu olusturuyoruz

    @Override
    public String toString() {
        return "bookingdatesPojo{" +
                " checkin= " + checkin +
                " checkout= " + checkout ;
    }
}
