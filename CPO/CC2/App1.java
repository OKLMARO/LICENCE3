import java.util.Random;

enum Direction { NORTH , SOUTH , EAST , WEST }

class Maze {
    final Prisoner prisoner;
    private Room currentRoom;
    final Room start;
    final Room exit;

    Maze(Room start, Room exit) {
        this.start = start;
        this.exit = exit;
        this.currentRoom = start;
        this.prisoner = new Prisoner();
    }

    void step(Direction direction) {
        Room newRoom = currentRoom.getNextRoom(direction);
        if (newRoom == null) {
            System.out.println("Cannot move!");
            prisoner.scream();
        } else {
            System.out.println("Moving " + direction);
            currentRoom = newRoom;
            if (newRoom == exit) {
                System.out.println("You are out of the maze");
                prisoner.win();
            } else {
                newRoom.action(this);
                prisoner.relieved();
            }
        }
    }

    boolean exited() {
        return (currentRoom == exit);
    }
}

class Prisoner {
    void scream() { System.out.println("Aaaaaah!"); }

    void win() { System.out.println("Wooohooooo!"); }

    void relieved() { System.out.println("Phew!"); }
}

class MazeBuilder {
    static Maze start(){
        return new Maze(new NormalRoom(),new NormalRoom());
    }
    static void linkRooms(Room room1, Room room2, Direction direction) {
        switch (direction) {
            case NORTH -> {
                room1.northRoom = room2;
                room2.southRoom = room1;
            }
            case SOUTH -> {
                room1.southRoom = room2;
                room2.northRoom = room1;
            }
            case WEST -> {
                room1.westRoom = room2;
                room2.eastRoom = room1;
            }
            case EAST -> {
                room1.eastRoom = room2;
                room2.westRoom = room1;
            }
        }

    }
}

abstract class Room {
    Room northRoom;
    Room southRoom;
    Room eastRoom;
    Room westRoom;

    abstract void action(Maze maze);

    Room getNextRoom(Direction direction) {
        switch (direction) {
            case SOUTH -> {
                return southRoom;
            }
            case NORTH -> {
                return northRoom;
            }
            case EAST -> {
                return eastRoom;
            }
            case WEST -> {
                return westRoom;
            }
            default -> {
                return null;
            }
        }
    }
}

class NormalRoom extends Room{

    @Override
    void action(Maze maze) {
        System.out.println("This is a normal room");
    }

}

public class App1 {
    public static void main(String[] args) {
        final Room[] rooms = new Room[4];
        for (int i = 0; i < 4; i++) {
            rooms[i] = new NormalRoom();
        }
        Maze maze = MazeBuilder.start();
        MazeBuilder.linkRooms(maze.start, rooms[0], Direction.EAST);
        for (int i = 0; i < 3; i++) {
            MazeBuilder.linkRooms(rooms[i], rooms[i + 1], Direction.EAST);
        }
        MazeBuilder.linkRooms(rooms[3], maze.exit, Direction.EAST);
        var random = new Random();
        while (!maze.exited()) {
            var d = random.nextInt(0, 4);
            maze.step(Direction.values()[d]);
        }
    }
}