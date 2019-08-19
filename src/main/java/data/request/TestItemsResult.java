package data.request;

import java.io.Serializable;
import java.util.List;

public class TestItemsResult implements Serializable {
    private final List<TestResult> testItemsResult;
    private final long testItemId;


    public TestItemsResult(List<TestResult> testItemsResult, long testItemId) {
        this.testItemsResult = testItemsResult;
        this.testItemId = testItemId;
    }

    public TestItemsResult() {
        this.testItemsResult = null;
        this.testItemId = 0;
    }

    public List<TestResult> getTestItemsResult() {
        return testItemsResult;
    }

    public long getTestItemId() {
        return testItemId;
    }
}
