package com.pragma.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pragma.api.domain.PageParameterResponse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericPageableResponse {

    private Object elements;

    private PageParameterResponse pagination;
}
