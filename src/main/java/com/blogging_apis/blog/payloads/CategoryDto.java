package com.blogging_apis.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer id;

    @NotBlank
    @Size(min = 4, max = 50)
    private String CategoryTitle;

    @Size(max=100)
    private String CategoryDescription;
}
