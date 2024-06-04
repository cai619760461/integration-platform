package com.incaier.integration.platform.response;

public class ResultBean {
    private String status = "";
    private String message = "";

    public ResultBean(String status, String message) {
        this.setStatus(status);
        this.setMessage(message);
    }

    public String getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public void setMessage(final String message) {
        this.message = message;
    }


}
