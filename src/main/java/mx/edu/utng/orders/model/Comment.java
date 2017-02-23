package mx.edu.utng.orders.model;

/**
 * Created by Jesús Eduardo Rico Sandoval on 23/02/2017.
 */

public class Comment {
    private  String id;
    private String comment;
    private String msg;
    private String created;

    public Comment(String id, String comment, String msg, String created) {
        this.id = id;
        this.comment = comment;
        this.msg = msg;
        this.created = created;
    }
    public Comment(){
        this("","","","");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", msg='" + msg + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
