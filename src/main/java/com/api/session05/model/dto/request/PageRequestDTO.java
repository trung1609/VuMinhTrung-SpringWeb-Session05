package com.api.session05.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDTO {
    private int page;
    private int size;
    private String sortBy;
    private String direction;
}
