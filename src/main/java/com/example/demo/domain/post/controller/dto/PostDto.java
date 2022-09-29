package com.example.demo.domain.post.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
  private Long seq;
  private String title;
  private String author;
  private String contents;
}
