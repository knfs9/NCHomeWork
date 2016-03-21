package exceptions;

public class FileCycleException extends RuntimeException {
    public FileCycleException(String message){
        super(message);
    }
    public FileCycleException(){
        super("File Cycle Exception");
    }
}
