package com.svynarchuk.simulation.semaphore;

import com.svynarchuk.simulation.semaphore.entity.Dealer;
import com.svynarchuk.simulation.semaphore.entity.Smoker;
import com.svynarchuk.simulation.semaphore.entity.Table;

import java.util.concurrent.Semaphore;

public class Runner {
    static Semaphore dealerSem = new Semaphore(1);
    static Semaphore smokerWithTobaccoSem = new Semaphore(0);
    static Semaphore smokerWithPaperSem = new Semaphore(0);
    static Semaphore smokerWithMatchesSem = new Semaphore(0);

    public static void main(String[] args) {
        Table table = new Table(smokerWithTobaccoSem, smokerWithPaperSem, smokerWithMatchesSem);
        new Dealer(table, dealerSem).start();
        new Smoker(smokerWithTobaccoSem, dealerSem, Constants.smokerWithTobacco).start();
        new Smoker(smokerWithPaperSem, dealerSem, Constants.smokerWithPaper).start();
        new Smoker(smokerWithMatchesSem, dealerSem, Constants.smokerWithMatches).start();
    }

}
