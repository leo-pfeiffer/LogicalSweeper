package model.agent.exceptions;

/**
 * Exception that is thrown when an agent has no more cells to probe.
 * */
public class NothingToProbeException extends RuntimeException{
    public NothingToProbeException(String message){
        super(message);
    }
}
