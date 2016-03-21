package exceptions;

/**
 * Signals that a cycle in the hierarchy of files
 */
public class FileCycleException extends RuntimeException {
    public FileCycleException(String message){
        super(message);
    }
    public FileCycleException(){
        super("File Cycle Exception");
    }
}
