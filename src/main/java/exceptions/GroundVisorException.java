package exceptions;

/**
 * Created by RTCCD on 06.03.2016.
 */
public class GroundVisorException extends RuntimeException {
    private String message;
    public GroundVisorException(String message){
        super(message);
        this.message = message;
    }
    public GroundVisorException(){
        super("Out of bound");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
