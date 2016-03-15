package ground;

import exceptions.GroundVisorException;

/**
 * Created by RTCCD on 06.03.2016.
 */
public class GroundVisor {
    private Ground ground;

    public boolean hasObstacles(int length, int width) throws GroundVisorException{
        if(length > ground.getLength() || width > ground.getWidth())
            throw new GroundVisorException();
        return ground.getCell(length, width).getState() == CellState.OCCUPIED;
    }
    public void setGround(Ground ground){
        this.ground = ground;
    }
}
