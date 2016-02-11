package zarda.io;

/**
 * Created by Ahmed Emad Barakat on 136 / 16 / 15.
 * Id: 2807
 */

class Monitor {

    private State[] state;
    private int[] doneEating;
    private int[] originalEating;

    public Monitor(int num, int doneEating[]) {
        state = new State[num];
        this.originalEating = doneEating;
        this.doneEating = new int[num];
        for (int i = 0; i < state.length; i++) {
            state[i] = State.THINKING;
            this.doneEating[i] = doneEating[i];
        }
    }

    public synchronized void pickUp(int id) throws InterruptedException {
        if (doneEating[id] > 0) {
            state[id] = State.HUNGRY;
            System.out.println("Philosopher " + id + " is hungry.\n");

            while (neighborIsEating(id)) {
                wait();
            }
            state[id] = State.EATING;
            System.out.println("Philosopher " + id + " picked up.\n");

            --doneEating[id];
            System.out.println("Philosopher " + id + " is eating for the " + (originalEating[id] - doneEating[id])
                    + " time.\n");
        }
    }

    private boolean neighborIsEating(int id) {
        if (state[(id + 1) % state.length] == State.EATING) {
            return true;
        }

        if (state[(id + state.length - 1) % state.length] == State.EATING) {
            return true;
        }

        return false;
    }

    public synchronized void putDown(int id) {
        if (state[id] == State.EATING) {
            state[id] = State.THINKING;
            System.out.println("Philosopher " + id + " put down.\n");
            notifyAll();
        }
    }

    public synchronized boolean isDone(int id) {
        return doneEating[id] == 0;
    }

    private enum State {THINKING, HUNGRY, EATING}

}
