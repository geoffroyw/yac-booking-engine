package yac.io.booking.engine.receiver;

/**
 * Created by geoffroy on 17/07/15.
 */
public class BookingRequest {

    private int id;

    private int customer_id;

    private int apartment_id;

    private String start_date;

    private String end_date;

    private int number_of_adult;

    private int number_of_children;

    private String state;

    private String created_at;

    private String updated_at;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getApartment_id() {
        return apartment_id;
    }

    public void setApartment_id(int apartment_id) {
        this.apartment_id = apartment_id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getNumber_of_adult() {
        return number_of_adult;
    }

    public void setNumber_of_adult(int number_of_adult) {
        this.number_of_adult = number_of_adult;
    }

    public int getNumber_of_children() {
        return number_of_children;
    }

    public void setNumber_of_children(int number_of_children) {
        this.number_of_children = number_of_children;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "id=" + id +
                ", customer_id=" + customer_id +
                ", apartment_id=" + apartment_id +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", number_of_adult=" + number_of_adult +
                ", number_of_children=" + number_of_children +
                ", state=" + state +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
