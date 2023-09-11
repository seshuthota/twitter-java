package com.codepup.twitter.model;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {

    private String oldPassword;
    private String newPassword;
}
