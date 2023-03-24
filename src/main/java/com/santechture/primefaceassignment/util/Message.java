package com.santechture.primefaceassignment.util;

import java.io.Serializable;

public class Message<T> implements Serializable {
   private static final long serialVersionUID = 1L;
	private Title title;
    private T message;
    private String status;
    public Message() {
      
        this(null, null);
    }
    public Message(Title title,T message) {
        setMessage(message);
        setTitle(title);
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public T getMessage() {
        return message;
    }
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Message [title=" + title.name() + ", message=" + message + ", status=" + status + "]";
	}
    
}
