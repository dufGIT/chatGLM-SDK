package cn.bugstack.chatglm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author df
 * @description 会话模型
 */
@Getter
@AllArgsConstructor
public enum Model {

    @Deprecated
    CHATGLM_6B_SSE("chatGLM_6b_SSE", "ChatGLM-6B 测试模型"),
    @Deprecated
    CHATGLM_LITE("chatglm_lite", "轻量版模型，适用对推理速度和成本敏感的场景"),
    @Deprecated
    CHATGLM_LITE_32K("chatglm_lite_32k", "标准版模型，适用兼顾效果和成本的场景"),
    @Deprecated
    CHATGLM_STD("chatglm_std", "适用于对知识量、推理能力、创造力要求较高的场景"),
    @Deprecated
    CHATGLM_PRO("chatglm_pro", "适用于对知识量、推理能力、创造力要求较高的场景"),

    /**
     * 智谱AI最新模型
     */
    CHATGLM_TURBO("chatglm_turbo", "适用于对知识量、推理能力、创造力要求较高的场景"),
    /**
     * 超拟人大模型，基于角色扮演的模型
     */
    CharacterGLM("character_glm", "持基于人设的角色扮演、超长多轮的记忆、千人千面的角色对话，" +
            "广泛应用于情感陪伴、游戏智能NPC、网红/明星/影视剧IP分身、数字人/虚拟主播、文字冒险游戏等拟人对话或游戏场景。");



    private final String code;
    private final String info;
}
