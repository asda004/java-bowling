package bowling.view;

import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.frame.Frames;
import bowling.domain.score.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-28 오후 1:24
 * Developer : Seo
 */
class GridMissTest {
    private Frames frames;
    private Player player;
    private int playerIndex;
    private int frameIndex;

    @BeforeEach
    void setUp() {
        frames = new Frames();
        player = new Player("aaa");
        playerIndex = 0;
        frameIndex = 0;
    }

    @Test
    void miss() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |      |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void miss_miss() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(1));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  2   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void miss_miss_miss() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(1));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  2   |      |      |      |      |      |      |      |      |      |");
    }

    @Test
    void miss_miss_miss_miss() {
        Frame frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(1));

        frames.next(frameIndex++);
        frame = frames.get(frameIndex);
        frame.stroke(playerIndex, new Pins(1));
        frame.spare(playerIndex, new Pins(1));

        assertThat(Grid.sum(frames, playerIndex))
                .isEqualTo("|      |  2   |  4   |      |      |      |      |      |      |      |      |");
    }
}
