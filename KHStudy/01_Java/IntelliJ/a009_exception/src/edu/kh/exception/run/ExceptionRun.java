package edu.kh.exception.run;

import edu.kh.exception.service.ExceptionService;

import java.io.IOException;

public class ExceptionRun {

    public static void main(String[] args) throws IOException {

        ExceptionService service = new ExceptionService();

        service.ex3();
    }
}
