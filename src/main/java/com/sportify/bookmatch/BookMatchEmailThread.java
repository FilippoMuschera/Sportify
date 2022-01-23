package com.sportify.bookmatch;

public class BookMatchEmailThread implements Runnable{
    @Override
    public void run() {
        BookMatchController bookMatchController = BookMatchController.getBookMatchControllerInstance();
        bookMatchController.sendEmail();
    }
}
