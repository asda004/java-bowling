package bowling.domain;

public interface State {
    Score getScore();

    boolean isFinished();
}
