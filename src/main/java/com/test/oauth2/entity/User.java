package com.test.oauth2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tblUser")
public class User {
    @Id
    @SequenceGenerator(name = "seqUserGen", allocationSize = 1, sequenceName = "seqUser")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUserGen")
    private Long seq;
    private String username;
    private String email;
    private String role;
    private String provider;
    private String providerid;
}
