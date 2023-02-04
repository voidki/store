package com.voidki.store.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: store
 * @package: com.voidki.store.domain.vo
 * @className: UserInfo
 * @author: voidki
 * @description:
 * @date: 2023/2/1 17:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Long id;
    /**
     * 头像
     */
    private String avatar;
    private String sex;
    private String email;
}
