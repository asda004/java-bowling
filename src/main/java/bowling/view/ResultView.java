package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Frames;
import bowling.domain.Symbol;
import bowling.domain.User;

/**
 * Created : 2020-12-16 오전 11:23
 * Developer : Seo
 */
public class ResultView {

    public static final String FORMAT_SPACE = "  %-3s |";
    public static final String HEADER_NAME = "| NAME |";
    public static final String BLANK = "";
    public static final String HEADER_FRAME_NO = "  %s  |";
    public static final String DELIMITER = "|";
    public static final int LAST_FRAME_NO = 10;

    public static void print(User user, Frames frames) {
        init(frames, user);
        eachStroke(frames, user);
    }

    private static void init(Frames frames, User user) {
        header(frames);
        empty(frames, user);
        emptyTotal(frames);
    }

    private static void header(Frames frames) {
        printf(HEADER_NAME, BLANK);
        frames.getFrames().forEach(frame -> printf(HEADER_FRAME_NO, frame.getFrameNoString()));
        space();
    }

    private static void empty(Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        frames.getFrames().forEach(frame -> printf(FORMAT_SPACE, BLANK));
    }

    private static void emptyTotal(Frames frames) {
        space();
        printf(DELIMITER + FORMAT_SPACE, "");
        frames.getFrames().forEach(frame -> printf(FORMAT_SPACE, BLANK));
    }

    private static void eachStroke(Frames frames, User user) {
        for (int frameNo = 1; frameNo < Frames.ALL_FRAMES; frameNo++) {
            untilThisFrameFirstStroke(frameNo, frames, user);
            untilThisFrameSecondStroke(frameNo, frames, user);
        }
        lastFrameFirstStroke(frames, user);
        lastFrameSecondStroke(frames, user);
    }

    private static void untilThisFrameFirstStroke(int frameNo, Frames frames, User user) {
        titleFirst(frameNo, frames);
        header(frames);
        gridPreviousFrameFirst(frameNo, frames, user);
        gridThisFrame(frameNo, frames);
        remainFrames(frameNo - 1);


        gridPre(frameNo, frames);

        remainFrames(frameNo - 1);
    }

    private static void gridPreviousFrameFirst(int frameNo, Frames frames, User user) {
        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (int previousNo = 1; previousNo < frameNo; previousNo++) {
            printf(FORMAT_SPACE, frames.get(previousNo - 1).getScore().getSymbol(previousNo));
        }
    }

    private static void gridThisFrame(int frameNo, Frames frames) {
        printf(FORMAT_SPACE, frames.get(frameNo - 1).getScore().getFirstSymbol());
    }

    private static void gridPre(int frameNo, Frames frames) {
        space();
        printf(DELIMITER + FORMAT_SPACE, "");
        int sum = 0;
        for (int previousNo = 1; previousNo < frameNo; previousNo++) {
            sum += frames.get(previousNo - 1).getScore().get();

            if (frames.get(previousNo - 1).getScore().getFirst() == Symbol.STRIKE.getScore()) {
                sum += frames.get(frameNo - 1).getScore().get();
            }

            if (frames.get(previousNo - 1).getScore().getFirst() != Symbol.STRIKE.getScore()) {
                printf(FORMAT_SPACE, String.valueOf(sum));
            }
        }

        if (frames.get(frameNo - 1).getScore().getFirst() != Symbol.STRIKE.getScore()) {
            printf(FORMAT_SPACE, String.valueOf(sum + frames.get(frameNo - 1).getScore().getFirst()));
        }
    }

    private static void untilThisFrameSecondStroke(int frameNo, Frames frames, User user) {
        if (isStrike(frameNo, frames)) {
            return;
        }

        titleSecond(frameNo, frames);

        header(frames);

        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (int previousNo = 0; previousNo < frameNo; previousNo++) {
            printf(FORMAT_SPACE, frames.get(previousNo).getScore().getSymbol(previousNo));
        }
        remainFrames(frameNo - 1);

        space();
        printf(DELIMITER + FORMAT_SPACE, "");
        int sum = 0;
        for (int previousNo = 0; previousNo < frameNo; previousNo++) {
            sum += frames.get(previousNo).getScore().get();
            printf(FORMAT_SPACE, String.valueOf(sum));
        }
        remainFrames(frameNo - 1);
    }

    private static void titleFirst(int frameNo, Frames frames) {
        space();
        space();
        printf(frameNo + "프레임 투구 : " + frames.get(frameNo - 1).getScore().getFirst(), "");
        space();
    }

    private static void titleSecond(int frameNo, Frames frames) {
        space();
        space();
        printf(frameNo + "프레임 투구 : " + frames.get(frameNo - 1).getScore().get(), "");
        space();
    }

    private static boolean isStrike(int frameNo, Frames frames) {
        return frames.get(frameNo - 1).getScore().getFirst() == Symbol.STRIKE.getScore();
    }

    private static void lastFrameFirstStroke(Frames frames, User user) {
        titleFirst(LAST_FRAME_NO, frames);

        header(frames);

        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (int frameNo = 1; frameNo < LAST_FRAME_NO; frameNo++) {
            printf(FORMAT_SPACE, frames.get(frameNo - 1).getScore().getSymbol(frameNo));
        }
        printf(FORMAT_SPACE, frames.get(LAST_FRAME_NO - 1).getScore().getFirstSymbol());

        space();
        printf(DELIMITER + FORMAT_SPACE, "");
        int sum = 0;
        for (int frameNo = 1; frameNo < LAST_FRAME_NO; frameNo++) {
            sum += frames.get(frameNo - 1).getScore().get();
            printf(FORMAT_SPACE, String.valueOf(sum));
        }
        printf(FORMAT_SPACE, String.valueOf(sum + frames.get(LAST_FRAME_NO - 1).getScore().get()));
    }

    private static void lastFrameSecondStroke(Frames frames, User user) {
        titleSecond(LAST_FRAME_NO, frames);

        header(frames);

        printf(DELIMITER + FORMAT_SPACE, user.getName());
        for (int frameNo = 1; frameNo < LAST_FRAME_NO + 1; frameNo++) {
            printf(FORMAT_SPACE, frames.get(frameNo - 1).getScore().getSymbol(frameNo));
        }

        space();
        printf(DELIMITER + FORMAT_SPACE, "");
        int sum = 0;
        for (int frameNo = 1; frameNo < LAST_FRAME_NO + 1; frameNo++) {
            sum += frames.get(frameNo - 1).getScore().get();
            printf(FORMAT_SPACE, String.valueOf(sum));
        }
    }

    private static void remainFrames(int i) {
        for (int j = i + 1; j < 10; j++) {
            printf(FORMAT_SPACE, "");
        }
    }

    private static void total(Frames frames) {
        space();
        printf(DELIMITER + FORMAT_SPACE, "");
        frames.getFrames().forEach(frame -> printf(FORMAT_SPACE, BLANK));
    }

    private static void printf(String format, String args) {
        System.out.printf(format, args);
    }

    private static void space() {
        System.out.println();
    }

    private ResultView() {
    }
}
