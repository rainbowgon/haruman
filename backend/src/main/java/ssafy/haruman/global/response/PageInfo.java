package ssafy.haruman.global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class PageInfo {

    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
