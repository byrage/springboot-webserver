package com.tistory.byrage.springboot.webserver.model;

import lombok.*;

@Getter
@ToString
public class Member {

    private Long id;
    private String name;
    private String email;

    @Builder(access = AccessLevel.PRIVATE)
    private Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static Member dummy(Long id) {
        return Member.builder()
                .id(id)
                .name("test user")
                .email("test@t.t")
                .build();
    }
}
