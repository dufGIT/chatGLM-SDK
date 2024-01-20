package cn.bugstack.chatglm.session;

import cn.bugstack.chatglm.model.ChatCompletionRequest;
import cn.bugstack.chatglm.model.ChatCompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.concurrent.CompletableFuture;

/**
 * @author df
 * @description 会话服务接口
 */
public interface OpenAiSession {

    EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;

    CompletableFuture<String> completions(ChatCompletionRequest chatCompletionRequest) throws InterruptedException;

}
