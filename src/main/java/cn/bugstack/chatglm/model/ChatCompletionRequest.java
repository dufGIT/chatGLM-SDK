package cn.bugstack.chatglm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author df
 * @description 请求大模型参数
 */
@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequest {

    /**
     * 模型
     */
    private Model model = Model.CHATGLM_TURBO;

    /**
     * 请求ID,保持唯一性
     */
    @JsonProperty("request_id")
    private String requestId = String.format("df-%d", System.currentTimeMillis());
    /**
     * 采样温度,控制输出的随机性,必须为正数,不能等于 0,默认值为 0.95
     * 值越大，会使输出更随机，更具创造性；值越小，输出会更加稳定或确定
     */
    private float temperature = 0.9f;
    /**
     * 用温度取样的另一种方法，称为核取样,不能等于 0 或 1，默认值为 0.7
     */
    @JsonProperty("top_p")
    private float topP = 0.7f;
    /**
     * 输入给模型的会话信息
     * 用户输入的内容；role=user
     * 挟带历史的内容；role=assistant
     */
    private List<Prompt> prompt;
    /**
     * 智普AI sse 固定参数 incremental = true 【增量返回】
     * false 为全量返回
     */
    private boolean incremental = true;
    /**
     * sseformat, 用于兼容解决sse增量模式okhttpsse截取data:后面空格问题, [data: hello]。只在增量模式下使用sseFormat。
     */
    private String sseFormat = "data";

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Prompt {
        private String role;
        private String content;
    }

    @Override
    public String toString() {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("request_id", requestId);
        paramsMap.put("prompt", prompt);
        paramsMap.put("incremental", incremental);
        paramsMap.put("temperature", temperature);
        paramsMap.put("top_p", topP);
        paramsMap.put("sseFormat", sseFormat);
        try {
            return new ObjectMapper().writeValueAsString(paramsMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
