package life.study.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木南
 * @version 1.0
 * @date 2023/7/3 15:00
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private Boolean showFirstPage;
    private Boolean showEndPage;
    private Boolean showPrevious;
    private Boolean showNext;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        pages = new ArrayList<>();
        // 总页码
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        // 页码越界
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        this.page = page;
        pages.add(page);
        // 判断page当前页之前是否能显示3页，page之后是否能显示3页，最多可以显示7页
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        // 当前页码不为1时显示上一页
        showPrevious = page != 1;
        // 当前页码不为最后一页时显示下一页
        showNext = !page.equals(totalPage);
        // 当页码导航栏不包含第一页时显示到首页
        showFirstPage = !pages.contains(1);
        // 当页码导航栏不包含最后一页时显示到尾页
        showEndPage = !pages.contains(totalPage);
    }
}
