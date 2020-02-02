package com.tistory.byrage.springboot.webserver.model;

import lombok.*;

@Getter
@ToString
public class Member {

    private Long id;
    private String name;
    private String email;

    @Builder
    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
