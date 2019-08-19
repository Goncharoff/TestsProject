package data.request;

import java.io.Serializable;

public class TestResult implements Serializable {

    private final long testId;
    private final long answerId;

    public TestResult(long testId, long answerId) {
        this.testId = testId;
        this.answerId = answerId;
    }

    public TestResult() {
        this.testId = 0;
        this.answerId = 0;
    }

    public long getTestId() {
        return testId;
    }

    public long getAnswerId() {
        return answerId;
    }
}
