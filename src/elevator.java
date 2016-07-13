import java.util.Comparator;
import java.util.List;

/**
 * Created by lizhengning1 on 7/12/16.
 */
public class elevator {
    public int currFloor;
    public int targetFloor;
    public Status status;
    public boolean Door;

    public elevator(){
        this.currFloor = 0;
        this.targetFloor = 0;
        this.status = Status.Stop;
    }

    public int getCurrFloor() {
        return this.currFloor;
    }
    public void setCurrFloor(int floor){
        this.currFloor = floor;
    }

    public int getTargetFloor() {
        return this.targetFloor;
    }
    public void setTargetFloor(int floor){
        this.targetFloor = floor;
    }

    public void moveUp(){
        status = Status.Moving;
        currFloor += 1;
    }

    public void moveDown(){
        status = Status.Moving;
        currFloor -= 1;
    }

    public void openDoor(){
        if (!Door) {
            Door = true;
        }
    }

    public void closeDoor(){
        if (Door) {
            Door = false;
        }
    }

    public void holdDoor(){
        Door = true;
    }

    public void alarm(){
        status = Status.ShutDown;
        System.out.println("Alarm!");
    }

    public void shutDown(){
        status = Status.ShutDown;
    }
}

enum Status{
    Stop, Moving, ShutDown
}

class User{
    Direction direction;
    int targetFloor;
    public void OutRequest(){
        Request req = new Request(direction);
    }

    public void InRequest(){
        Request req = new Request(targetFloor);
    }
}

class Handler{

    List<Request> requestList;
    int nearest = Integer.MAX_VALUE;

    public void addRequests(Request req) {
        synchronized(req){
            requestList.add(req);
        }
    }

    public Request getNextRequest(){
        elevator e = new elevator();
        int currentFloor = e.getCurrFloor();
        Request next = null;
        for (Request req : requestList) {
            if (Math.abs(req.getTargetfloor() - currentFloor) < nearest) {
                next = req;
                nearest = req.getTargetfloor() - currentFloor;
            }
        }
        return next;
    }
}

class Request{
    public int targetfloor;
    public Direction direction;

    public Request(Direction direction){
        this.direction = direction;
    }
    public Request(int floor) {
        setTargetfloor(floor);
//        this.direction = direction;
    }

    public void setTargetfloor(int floor){
        this.targetfloor = floor;
    }
    public int getTargetfloor(){
        return this.targetfloor;
    }
}

enum Direction{
    UP, DOWN
}

