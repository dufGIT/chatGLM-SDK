package cn.bugstack.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author df
 * @description 角色
 */
@Getter
@AllArgsConstructor
public enum Role {
    /**
     * user 用户输入的内容，role位user
     */
    user("user"),
    /**
     * 模型生成的内容，role位assistant
     */
    assistant("assistant");

    private final String code;

}
