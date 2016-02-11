package zarda.io;

import java.util.Scanner;

/**
 * Created by Ahmed Emad Barakat on 136 / 16 / 15.
 * Id: 2807
 */

public class Main {

    public static void main(String[] args) {
        int num;
        boolean error = false;
        int[] eatings = new int[1];

        Scanner reader = new Scanner(System.in);
        System.out.print("Enter number of Philosophers: ");
        num = reader.nextInt();

        if (num < 0) {
            error = true;
        }

        if (!error) {
            eatings = new int[num];

            for (int i = 0; i < num; ++i) {
                System.out.print("Enter number of eatings for Philosopher " + (i + 1) + ": ");
                eatings[i] = reader.nextInt();
                if (eatings[i] < 0) {
                    error = true;
                    break;
                }
            }
        }


        if (!error) {
            Philosopher[] philosophers = new Philosopher[num];
            Monitor monitor = new Monitor(num, eatings);

            for (int i = 0; i < num; ++i) {
                philosophers[i] = new Philosopher(i, monitor);
                new Thread(philosophers[i]).start();
            }
        } else {
            System.out.println("Error!!!!");
        }
    }

}
