package com.example.counter.bean.res;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CaptchaRes {
    private String id;

    private String imageBase64;

}
