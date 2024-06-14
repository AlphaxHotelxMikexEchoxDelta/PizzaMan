package Pizza;

import java.util.List;

public class Ingredient {

    private String id ;
    private String name ;
    private String expiration ;
    private String deliveryTime ;
    private String status ;

    public Ingredient(String id, String name, String expiration, String deliveryTime, String status) {
        this.id = id;
        this.name = name;
        this.expiration = expiration;
        this.deliveryTime = deliveryTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
