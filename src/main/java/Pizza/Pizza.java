package Pizza;

public class Pizza implements LaPizzaDeLaMama {

    private String id ;
    private String recette ;
    private String status ;
    private String startTime ;
    private String expirationTime ;
    private String readyTime ;

    public Pizza(String id, String recette, String status, String startTime, String expirationTime, String readyTime) {
        this.id = id;
        this.recette = recette;
        this.status = status;
        this.startTime = startTime;
        this.expirationTime = expirationTime;
        this.readyTime = readyTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecette() {
        return recette;
    }

    public void setRecette(String recette) {
        this.recette = recette;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getReadyTime() {
        return readyTime;
    }

    public void setReadyTime(String readyTime) {
        this.readyTime = readyTime;
    }
}
