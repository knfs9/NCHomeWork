package rover;

import ground.GroundVisor;

/**
 * Created by RTCCD on 01.03.2016.
 */
public class Rover implements Turnable, Moveable, ProgramFileAware {
    private int x;
    private int y;
    private Direction direction;
    private GroundVisor visor;
    private RoverCommandParser programParser;

    public Rover(){
        visor = new GroundVisor();
        programParser = new RoverCommandParser(this);
    }

    @Override
    public void move(int x, int y) {
        if(visor.hasObstacles(x,y)){
            System.out.println("obtacles");
        }
        this.x = x;
        this.y = y;
        System.out.println("Move to x: " + x + " y: " + y);
    }
    @Override
    public void turnTo(Direction direction) {
        System.out.println("Turn to " + direction.name());

    }

    public GroundVisor getVisor(){
        return visor;
    }

    @Override
    public void executeProgramFile(String filename){
        programParser.parse(filename);
        while (programParser.hasNext()){
            programParser.readNextCommand().execute();
        }
    }



}
