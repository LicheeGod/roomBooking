package com.demo.roombooking.common.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Jackie
 */
@Data
@NoArgsConstructor
public class PageableBuilder {
    /**
     * 默认排序字段
     */
    private static final String DEFAULT_SORT_FIELD = "id";
    /**
     * 默认排序方向
     */
    private static final String DEFAULT_SORT_DIR = "DESC";
    /**
     * 页码，默认为1
     */
    private Integer pageNum = 1;
    /**
     * 每页大小，默认为10
     */
    private Integer pageSize = 10;
    /**
     * 排序字段，默认根据id排序
     */
    private String sort = DEFAULT_SORT_FIELD;
    /**
     * 排序方向，默认降序
     */
    private String dir = DEFAULT_SORT_DIR;

    /**
     * @author hanvey
     */
    public Pageable getPageable() {
        Sort sort = new Sort(Sort.Direction.ASC, this.sort);

        if (this.dir.toLowerCase().equals("desc")) {
            sort = new Sort(Sort.Direction.DESC, this.sort);
        }

        return PageRequest.of(this.pageNum - 1, this.pageSize, sort);
    }

//    /**
//     * 多种排序方式
//     */
//    @JsonProperty("order")
//    private List<Map<String, String>> orderMaps;
//
//    /**
//     * @author vinko
//     */
//    public Pageable getPageable() {
//
//        if (orderMaps == null || orderMaps.size() == 0) {
//            return getSinglePageable();
//        } else {
//            return getMoreSortPageable();
//        }
//    }
//
//    /**
//     * @author vinko
//     */
//    public Pageable getSinglePageable() {
//
//        Sort sort = Sort.by(Sort.Direction.ASC, this.sort);
//
//        if (DEFAULT_SORT_DIR.equals(this.dir)) {
//            sort = Sort.by(Sort.Direction.DESC, this.sort);
//        }
//        return PageRequest.of(this.pageNum - 1, this.pageSize, sort);
//
//    }
//
//    /**
//     * @author vinko
//     */
//    public Pageable getMoreSortPageable() {
//
//        List<Sort.Order> orders = new ArrayList<>();
//
//        if (orderMaps == null) {
//            return getSinglePageable();
//        }
//
//        orderMaps.forEach(
//                orderMaps -> orderMaps.forEach(
//                        (sort, dir) -> {
//                            orders.add(new Sort.Order(Sort.Direction.fromString(dir), sort));
//                        }
//                )
//        );
//
//        Sort sort = Sort.by(orders);
//
//        return PageRequest.of(this.pageNum - 1, this.pageSize, sort);
//    }

    public PageableBuilder buildPage(Integer pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public PageableBuilder buildPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public PageableBuilder buildSort(String sort) {
        this.sort = sort;
        return this;
    }

    public PageableBuilder buildDir(String dir) {
        this.dir = dir;
        return this;
    }

    public PageableBuilder buildSort(String sort, String dir) {
        this.sort = sort;
        this.dir = dir;
        return this;
    }
}

