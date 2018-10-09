package com.github.tonydeng.demo.rpc.armeria.facade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String title;
    private String author;
    private String ISBN;
    private List<String> keys;
    private int page;
}
