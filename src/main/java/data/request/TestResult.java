package data.request;

import java.io.Serializable;

public class TestResult implements Serializable {

    private final Long testId;
    private final Integer corrects;
    private final Integer total;

    public TestResult(Long testId, Integer corrects, Integer total) {
        this.testId = testId;
        this.corrects = corrects;
        this.total = total;
    }

    public TestResult() {
        this.testId = null;
        this.corrects = null;
        this.total = null;
    }

    public Long getTestId() {
        return testId;
    }

    public Integer getCorrects() {
        return corrects;
    }

    public Integer getTotal() {
        return total;
    }

}
