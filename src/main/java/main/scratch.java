package main;

import service.box.MysteryBox;

public class scratch {
    public static void main(String[] args) {
        System.out.println("hello\n");

        MysteryBox mb = new MysteryBox();
        mb.init();
        mb.printOp();

        for (int i=0; i<20; i++) {
            mb.run();
            System.out.println(mb);
        }
    }
}
