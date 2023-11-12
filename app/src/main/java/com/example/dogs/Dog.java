package com.example.dogs;

public class Dog {

    String message;
    String status;

    public Dog(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
