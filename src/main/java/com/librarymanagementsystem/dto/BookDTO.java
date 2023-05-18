package com.librarymanagementsystem.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @NotEmpty(message = "book id cannot be null")
    private int id;

    private String bookName;

    private String authorName;

    private String description;

    private String price;


}
