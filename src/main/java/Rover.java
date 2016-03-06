/**
 * Created by RTCCD on 01.03.2016.
 */
public class Rover implements Turnable, Moveable {
    private int x;
    private int y;
    private Direction direction;
    private GroundVisor visor;

    public Rover(){
        visor = new GroundVisor();
    }
    @Override
    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println("Move to x: " + x + " y: " + y);
    }
    @Override
    public void turnTo(Direction direction) {
        System.out.println("Turn to" + direction.name());
    }
    public GroundVisor getVisor(){
        return visor;
    }

}
