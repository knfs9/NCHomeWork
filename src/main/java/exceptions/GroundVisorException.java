package exceptions;

/**
 * Created by RTCCD on 06.03.2016.
 */
public class GroundVisorException extends RuntimeException {

    public GroundVisorException(String message){
        super(message);
    }
    public GroundVisorException(){
        super("Out of bound");
    }

}
