package com.korit.mini_post.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class ReqSigninDto {
    @ApiModelProperty(value = "사용자이름", example = "user1234", required = true)
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "사용자 이름은 영문 대소문자, 숫자, 밑줄(_) 만 포함할 수 있습니다.(최소 4자 이상, 16자 이하)")
    private String username;

    @ApiModelProperty(value = "비밀번호", example = "User12345678!", required = true)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>])[A-Za-z\\d!@#$%^&*(),.?\":{}|<>]{8,20}$", message = "영어 대소문자, 숫자, 특수문자(!@#$%^&*(),.?\":{}|<>)를 하나 이상 모두 포함하며 8자리 이상 20자리 이하로 입력해야 합니다.")
    private String password;
}
