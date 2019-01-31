package york.test;

/**
 * 結果有
 * 1. 一色，俗稱豹子
 * 2. 十八啦，一對搭配兩個六
 * 3. 點數，一對搭配另外兩顆非一對骰子點數總和
 * 4. 沒點，某點數有三個一樣或者每個點數都不一樣
 */
public enum ResultComment {

    /**
     * 一色
     */
    SAME_COLOR(4),

    /**
     * 十八啦
     */
    TWELVE(3),

    /**
     * 點數
     */
    N_POINTS(2),

    /**
     * 沒點
     */
    NO_POINTS(1);

    private final int resultComment;

    ResultComment(final int resultComment) {
        this.resultComment = resultComment;
    }

    public int getLevelCode() {
        return this.resultComment;
    }

}
