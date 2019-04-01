package to.lova.spring.blaze.model;

import java.io.Serializable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public final class OffsetBasedPageRequest implements Pageable, Serializable {

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters
     * applied.
     *
     * @param offset
     *            zero-based offset.
     * @param limit
     *            the size of the elements to be returned.
     */
    public static OffsetBasedPageRequest of(long offset, long limit) {
        return new OffsetBasedPageRequest(offset, limit);
    }

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters
     * applied.
     *
     * @param offset
     *            zero-based offset.
     * @param limit
     *            the size of the elements to be returned.
     * @param sort
     *            can be {@literal null}.
     */
    public static OffsetBasedPageRequest of(long offset, long limit,
            Sort sort) {
        return new OffsetBasedPageRequest(offset, limit, sort);
    }

    /**
     * Creates a new {@link OffsetBasedPageRequest} with sort parameters
     * applied.
     *
     * @param offset
     *            zero-based offset.
     * @param limit
     *            the size of the elements to be returned.
     * @param direction
     *            the direction of the {@link Sort} to be specified, can be
     *            {@literal null}.
     * @param properties
     *            the properties to sort by, must not be {@literal null} or
     *            empty.
     */
    public static OffsetBasedPageRequest of(long offset, long limit,
            Direction direction, String... properties) {
        return new OffsetBasedPageRequest(offset, limit, direction, properties);
    }

    private long limit;
    private long offset;
    private final Sort sort;

    private OffsetBasedPageRequest(long offset, long limit, Sort sort) {
        if (offset < 0) {
            throw new IllegalArgumentException(
                    "Offset index must not be less than zero!");
        }

        if (limit < 1) {
            throw new IllegalArgumentException(
                    "Limit must not be less than one!");
        }
        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }

    private OffsetBasedPageRequest(long offset, long limit,
            Sort.Direction direction, String... properties) {
        this(offset, limit, new Sort(direction, properties));
    }

    private OffsetBasedPageRequest(long offset, long limit) {
        this(offset, limit, Sort.unsorted());
    }

    @Override
    public int getPageNumber() {
        long pageNumber = Math.floorDiv(this.offset, this.limit);
        return Math.toIntExact(pageNumber);
    }

    @Override
    public int getPageSize() {
        return Math.toIntExact(this.limit);
    }

    @Override
    public long getOffset() {
        return this.offset;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return new OffsetBasedPageRequest(this.getOffset() + this.getPageSize(),
                this.getPageSize(), this.getSort());
    }

    public OffsetBasedPageRequest previous() {
        return this.hasPrevious()
                ? new OffsetBasedPageRequest(
                        this.getOffset() - this.getPageSize(),
                        this.getPageSize(), this.getSort())
                : this;
    }

    @Override
    public Pageable previousOrFirst() {
        return this.hasPrevious() ? this.previous() : this.first();
    }

    @Override
    public Pageable first() {
        return new OffsetBasedPageRequest(0, this.getPageSize(),
                this.getSort());
    }

    @Override
    public boolean hasPrevious() {
        return this.offset > this.limit;
    }

}
