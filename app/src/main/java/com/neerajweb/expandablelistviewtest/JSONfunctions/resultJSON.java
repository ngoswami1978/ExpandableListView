package com.neerajweb.expandablelistviewtest.JSONfunctions;

/**
 * Created by Admin on 16/02/2016.
 */
public class resultJSON {
    private int id;
    private int success;
    private String message;

    public resultJSON() {}

    public resultJSON(int id,int success, String message) {
        super();
        this.id = id;
        this.success = success;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
