package com.example.demo.model.service;

import lombok.*; //어노테이션 자동 생성
import com.example.demo.model.domain.*;

import jakarta.validation.constraints.*;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Data // getter, setter, toString, equals 등 자동 생성
public class AddMemberRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-힣]+$")
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")
    private String password;

    @NotNull
    @Min(19)
    @Max(90)
    private String age;

    private String mobile;
    private String address;

    public Member toEntity() { // Member 생성자를 통해 객체 생성
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .age(age)
                .mobile(mobile)
                .address(address)
                .build();
    }
    
}
