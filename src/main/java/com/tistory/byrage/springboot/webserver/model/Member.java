package com.tistory.byrage.springboot.webserver.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Member {

    private Long id;
    private String name;
    private String email;
}
