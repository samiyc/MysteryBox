package main;

import service.box.MysteryBox;
import service.solver.LogicSolver;

public class EntryPoint {
    public static void main(String[] args) {
        System.out.println("EntryPoint.main() : hello !\n");

        //Initialisation de la MysteryBox
        MysteryBox mb = new MysteryBox();
        mb.init(4, 4, 8);
        mb.printOp();
        mb.debug();

        //Initialisation du solver
        LogicSolver solver = new LogicSolver(mb, 16);

        //Analyse iterative
        for (int i=0; i<4; i++) {
            solver.solve(100);
            solver.cleanUp();
        }
    }
}
