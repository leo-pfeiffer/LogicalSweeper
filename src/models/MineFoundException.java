package models;

/**
 * Exception that is thrown when an agent proves a mine.
 * */
public class MineFoundException extends RuntimeException{
    public MineFoundException(String message){
        super(message);
    }
}
