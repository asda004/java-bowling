package bowling.domain;

import bowling.domain.frame.NormalFrame;
import bowling.domain.score.Pins;
import bowling.exception.CannotCalculateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created : 2020-12-16 오전 9:44
 * Developer : Seo
 */
class FrameTest {
    private NormalFrame frame;

    @BeforeEach
    void setUp() {
        frame = new NormalFrame(1);
    }

    @DisplayName("생성")
    @Test
    void constructor()  {
        assertThat(frame).isNotNull().isInstanceOf(NormalFrame.class);
        assertThat(frame.getFrameNo()).isEqualTo(1);
    }

    @DisplayName("스트라이크")
    @Test
    void bowl_strike() {
    }

    @DisplayName("스트라이크면 다음 프레임")
    @Test
    void bowl_isStrike() {
    }

    @DisplayName("첫 구가 스트라이크 아니면 다음 구")
    @Test
    void bowl_isFirst() {
    }

    @DisplayName("2구 이후 다음 프레임")
    @Test
    void bowl_isSecond() {
    }

    @DisplayName("남아있는 핀")
    @Test
    void remainPins() {
    }
}
