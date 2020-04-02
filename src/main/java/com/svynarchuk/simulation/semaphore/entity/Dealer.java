package com.svynarchuk.simulation.semaphore.entity;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Dealer extends Thread {
    private final Random rnd = new Random();
    private final Table table;
    private final Semaphore dealer;

    public Dealer(Table table, Semaphore dealer) {
        this.table = table;
        this.dealer = dealer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                dealer.acquire();
                provideIngredients();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void provideIngredients() {
        int temp = rnd.nextInt(3);
        if (temp == 0) {
            System.out.println("<< Dealer >> puts paper and matches on the table");
            table.putPaper();
            table.putMatches();
        }
        if (temp == 1) {
            System.out.println("<< Dealer >> puts pushing tobacco and matches on the table");
            table.putTobacco();
            table.putMatches();
        }
        if (temp == 2) {
            System.out.println("<< Dealer >> puts pushing tobacco and paper on the table");
            table.putTobacco();
            table.putPaper();
        }
    }

}
