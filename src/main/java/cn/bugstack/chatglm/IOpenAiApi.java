package cn.bugstack.chatglm;

import cn.bugstack.chatglm.model.ChatCompletionRequest;
import cn.bugstack.chatglm.model.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author df
 * @description OpenAi 接口，用于扩展通用类服务
 */
public interface IOpenAiApi {

    String v3_completions = "api/paas/v3/model-api/{model}/sse-invoke";

    /**
     * 使用了Retrofit2库，用于调用RESTful Web服务。
     * Single<ChatCompletionResponse> 返回响应类型
     */
    @POST(v3_completions)
    Single<ChatCompletionResponse> completions(@Path("model") String model, @Body ChatCompletionRequest chatCompletionRequest);

}
