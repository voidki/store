package com.voidki.store.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: store
 * @package: com.voidki.store.domain.vo
 * @className: UserLoginVo
 * @author: voidki
 * @description:
 * @date: 2023/2/1 17:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginVo {
    private String token;
    private UserInfo userInfo;
}
