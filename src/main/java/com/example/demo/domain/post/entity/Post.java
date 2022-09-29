package com.example.demo.domain.post.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "POST")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "POST_SEQ", length = 25)
  private Long seq;

  @Column(name = "TITLE", length = 255)
  private String title;

  @Column(name = "AUTHOR", length = 255)
  private String author;

  @Column(name = "CONTENTS", length = 4000)
  private String contents;
}
