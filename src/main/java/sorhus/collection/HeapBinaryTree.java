package sorhus.collection;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @author: anton.sorhus@gmail.com
 */
public class HeapBinaryTree<T> {

    private enum State{INIT,LEFT,MIDDLE,RIGHT,BACK,GO_FAR_INIT,GO_FAR};
    private static class StateHolder {
        State state = State.INIT;
        boolean done = false;
    }

//    private T[] heap;
    private int[] heap;

//    public HeapBinaryTree(T[] heap) {
//        this.heap = heap;
//    }

    public HeapBinaryTree(int[] heap) {
        this.heap = heap;
    }

    public int parent(int i) {
        return (i-1)/2;
    }

    public int left(int i) {
        return i*2 + 1;
    }

    public int right(int i) {
        return i*2 + 2;
    }

    public Object[] inOrder() {
        Object[] result = new Object[heap.length];
        StateHolder state = new StateHolder();
        int i = first(state);
        int j = 0;
        while(i > -1) {
            result[j++] = heap[i];
            i = next(i, state);
        }
        return result;
    }

    public void printInOrder() {
        printInOrder(new PrintWriter(System.out), " ");
    }

    public void printInOrder(Writer writer, String sep) {
        try {
            StateHolder state = new StateHolder();
            int i = first(state);
            while(i > 0 || !state.done) {
//                writer.write(heap[i].toString());
                writer.write(heap[i]);
                writer.write(sep);
                i = next(i, state);
            }
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPreOrder() {
        printPreOrder(new PrintWriter(System.out), " ");
    }

    public void printPreOrder(Writer writer, String sep) {
        try {
            for(int i = 0; i < heap.length; i++) {
//                writer.write(heap[i].toString());
                writer.write(heap[i]);
                writer.write(sep);
            }
            writer.write("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    private int first(StateHolder state) {
        return next(-1, state);
    }

    // O(1) call stack + heap space
    private int next(int i, StateHolder state) {
        switch (state.state) {
            case INIT:
                state.state = State.LEFT;
                return (int) Math.pow(2, (int) (Math.log(heap.length) / Math.log(2))) - 1;
            case LEFT:
                state.state = State.MIDDLE;
                return parent(i);
            case MIDDLE:
                if(i == 0) {
                    state.done = true;
                }
                if(right(i) < heap.length) {
                    state.state = State.GO_FAR_INIT;
                    return next(right(i), state);
                } else if(i % 2 == 1) {
                    return parent(i);
                } else {
                    state.state = State.BACK;
                    return next(parent(i), state);
                }
            case RIGHT:
                state.state = State.BACK;
                return next(parent(i), state);
            case BACK:
                while(i % 2 == 0) {
                    i = parent(i);
                    if(state.done && i == 0) {
                        return -1;
                    }
                }
                state.state = State.LEFT;
                return next(i, state);
            case GO_FAR_INIT:
                if(left(i) < heap.length) {
                    state.state = State.GO_FAR;
                    return next(left(i), state);
                } else {
                    state.state = State.RIGHT;
                    return i;
                }
            case GO_FAR:
                while(left(i) < heap.length) {
                    i = left(i);
                }
                state.state = State.LEFT;
                return i;
            default:
                throw new RuntimeException();
        }
    }

}
