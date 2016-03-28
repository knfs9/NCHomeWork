package rover;

import ground.GroundVisor;


public class Rover implements Turnable, Moveable, ProgramFileAware {
    private int x;
    private int y;
    private Direction direction;
    private GroundVisor visor;
    private TextRoverCommandParser programParser;
    private XmlRoverCommandParser xmlProgramParser;

    public Rover(){
        visor = new GroundVisor();
        programParser = new TextRoverCommandParser(this);
        xmlProgramParser = new XmlRoverCommandParser(this);
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
       /* programParser.parse(filename);
        while (programParser.hasNext()){
            programParser.readNextCommand().execute();
        }*/
        xmlProgramParser.parse(filename);
        while (xmlProgramParser.hasNext()){
            xmlProgramParser.readNextCommand().execute();
        }
    }



}
