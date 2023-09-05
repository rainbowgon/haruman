package ssafy.haruman.global.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageInfo {

    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
