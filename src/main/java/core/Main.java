package core;

import core.annotations.Autumn;

public class Main {
    public static void main (String[] args){
        Calculator calc = new Calculator();
        Autumn server = new Autumn();
        server.addMethods(calc);
        server.start(7080);
    }
}
