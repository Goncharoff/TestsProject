package data.request;

import java.io.Serializable;

public class PaginationRequest implements Serializable {
    private final int limit;
    private final int offset;

    public PaginationRequest(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
