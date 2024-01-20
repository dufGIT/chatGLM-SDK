package cn.bugstack.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 小傅哥，微信：fustack
 * @description 大模型反馈消息类型
 */
@Getter
@AllArgsConstructor
public enum EventType {

    // 事件流开启
    add("add", "增量"),
    // 数据接收完毕，关闭事件流
    finish("finish", "结束"),
    // 平台服务或者模型异常，响应的异常事件
    error("error", "错误"),
    // 中断事件，例如：触发敏感词
    interrupted("interrupted", "中断");

    private final String code;
    private final String info;

}
