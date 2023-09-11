package sathchalo.com;

public class Message_Model {

    String uId, messag;
    Long timestamp;

    public Message_Model(String uId, String messag, Long timestamp) {
        this.uId = uId;
        this.messag = messag;
        this.timestamp = timestamp;
    }

    public Message_Model(String uId, String messag) {
        this.uId = uId;
        this.messag = messag;
    }

    public Message_Model() {
    }

    public String getuId() {
        return uId;
    }

    public String getMessag() {
        return messag;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void setMessag(String messag) {
        this.messag = messag;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
