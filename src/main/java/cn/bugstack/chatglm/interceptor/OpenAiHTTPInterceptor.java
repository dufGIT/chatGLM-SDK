package cn.bugstack.chatglm.interceptor;

import cn.bugstack.chatglm.session.Configuration;
import cn.bugstack.chatglm.utils.BearerTokenUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author df
 * @description 接口拦截器，处理token的
 */
public class OpenAiHTTPInterceptor implements Interceptor {

    /**
     * 智普Ai，Jwt加密Token
     */
    private final Configuration configuration;

    public OpenAiHTTPInterceptor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public @NotNull Response intercept(Chain chain) throws IOException {
        // 1. 获取原始 Request
        Request original = chain.request();

        // 2. 构建请求
        Request request = original.newBuilder()
                .url(original.url())
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(configuration.getApiKey(), configuration.getApiSecret()))
                .header("Content-Type", Configuration.JSON_CONTENT_TYPE)
                .header("User-Agent", Configuration.DEFAULT_USER_AGENT)
                .header("Accept", Configuration.SSE_CONTENT_TYPE)
                .method(original.method(), original.body())
                .build();

        // 3. 返回执行结果
        return chain.proceed(request);
    }

}
