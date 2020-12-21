package bowling.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created : 2020-12-21 오전 11:22
 * Developer : Seo
 */
public class States {
    private final List<State> states;

    public States() {
        this.states = new LinkedList<>();
    }

    public void add(State state) {
        this.states.add(state);
    }

    public State getState(int i) {
        if (states.isEmpty()) {
            return new None();
        }
        return this.states.get(i - 1);
    }

    public void set(int frameNo, State state) {
        this.states.set(frameNo, state);
    }

    public int size() {
        return states.size();
    }
}
