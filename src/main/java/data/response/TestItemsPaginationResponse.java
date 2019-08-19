package data.response;

import data.business.TestItem;

import java.io.Serializable;
import java.util.List;

public class TestItemsPaginationResponse implements Serializable {
    private final long totalNumber;
    private final List<TestItem> testItems;

    public TestItemsPaginationResponse(long totalNumber, List<TestItem> testItems) {
        this.totalNumber = totalNumber;
        this.testItems = testItems;
    }

    public long getTotalNumber() {
        return totalNumber;
    }

    public List<TestItem> getTestItems() {
        return testItems;
    }
}
