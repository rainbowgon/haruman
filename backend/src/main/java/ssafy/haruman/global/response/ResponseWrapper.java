package ssafy.haruman.global.response;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseWrapper<T> {

    private PageInfo pageInfo;
    private String message;
    private T data;
}