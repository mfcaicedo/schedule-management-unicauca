package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageParameterResponse {

    private Integer page;

    private Integer size;

    private Long totalNumberElements;

    private Integer totalNumberPage;
}
