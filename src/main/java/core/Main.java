package core;

public class Main {
    public static void main (String[] args){
//        var number = Runtime.getRuntime().availableProcessors();
//        System.out.println(number);
        ServerRequestHandler server = new ServerRequestHandler();
        server.run();
    }
}
