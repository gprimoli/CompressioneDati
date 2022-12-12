package it.unisa.di.exception;

public class ReadRowException extends Exception{
    public ReadRowException(String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
