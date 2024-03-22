package com.hth.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserInfoVo {
    //头像
    private String avatar;

    private String email;

    /**
     * 主键
     */
    private Long id;

    /**
     * 昵称
     */
    private String nickName;


    private String sex;


}
