package cn.bugstack.chatglm.test;

import cn.bugstack.chatglm.model.*;
import cn.bugstack.chatglm.session.Configuration;
import cn.bugstack.chatglm.session.OpenAiSession;
import cn.bugstack.chatglm.session.OpenAiSessionFactory;
import cn.bugstack.chatglm.session.defaults.DefaultOpenAiSessionFactory;
import cn.bugstack.chatglm.utils.BearerTokenUtils;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @author df
 * @description 在官网申请 ApiSecretKey <a href="https://open.bigmodel.cn/usercenter/apikeys">ApiSecretKey</a>
 */
@Slf4j
public class ApiTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {
        // 1. 配置文件
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://open.bigmodel.cn/");
        // b902f8f395232c080bf336e242b65378.uGLnB16Lrm0iRcan
        configuration.setApiSecretKey("d570f7c5d289cdac2abdfdc562e39f3f.trqz1dH8ZK6ED7Pg");
        configuration.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 3. 开启会话
        this.openAiSession = factory.openSession();
    }

    /**
     * 流式对话
     */
    @Test
    public void test_completions() throws JsonProcessingException, InterruptedException {
        // 入参；模型、请求信息
        ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel(Model.CHATGLM_TURBO); // chatGLM_6b_SSE、chatglm_lite、chatglm_lite_32k、chatglm_std、chatglm_pro
        // 每一次SSE回馈时会全量返回
        request.setIncremental(false);
        request.setPrompt(new ArrayList<ChatCompletionRequest.Prompt>() {
            private static final long serialVersionUID = -7988151926241837899L;

            {
                add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("你好请问你是")
                        .build());
            }
        });

        // 请求ChatGLM
        openAiSession.completions(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
                log.info("测试结果 onEvent：{}", response.getData());
                // type 消息类型，add 增量，finish 结束，error 错误，interrupted 中断
                if (EventType.finish.getCode().equals(type)) {
                    ChatCompletionResponse.Meta meta = JSON.parseObject(response.getMeta(), ChatCompletionResponse.Meta.class);
                    log.info("[输出结束] Tokens {}", JSON.toJSONString(meta));
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                log.info("对话完成");
            }

        });
        // 将主线程阻塞，否则子线程还没结束，整个线程就结束了。
        new CountDownLatch(1).await();
    }

    /**
     * 同步请求
     */
    @Test
    public void test_completions_future() throws ExecutionException, InterruptedException {
        // 入参；模型、请求信息
        ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel(Model.CHATGLM_TURBO); // chatGLM_6b_SSE、chatglm_lite、chatglm_lite_32k、chatglm_std、chatglm_pro
        request.setPrompt(new ArrayList<ChatCompletionRequest.Prompt>() {
            private static final long serialVersionUID = -7988151926241837899L;

            {
                add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("请写个java冒泡排序")
                        .build());
            }
        });

        CompletableFuture<String> future = openAiSession.completions(request);
        // 阻塞在获取反馈结果里,直到结果反馈完毕。
        String response = future.get();

        log.info("测试结果：{}", response);
    }


    @Test
    public void test_curl() {
        // 1. 配置文件
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://open.bigmodel.cn/");
        configuration.setApiSecretKey("b902f8f395232c080bf336e242b65378.uGLnB16Lrm0iRcan");

        // 2. 获取Token
        String token = BearerTokenUtils.getToken(configuration.getApiKey(), configuration.getApiSecret());
        log.info("1. 在智谱Ai官网，申请 ApiSeretKey 配置到此测试类中，替换 setApiSecretKey 值。 https://open.bigmodel.cn/usercenter/apikeys");
        log.info("2. 运行 test_curl 获取 token：{}", token);
        log.info("3. 将获得的 token 值，复制到 curl.sh 中，填写到 Authorization: Bearer 后面");
        log.info("4. 执行完步骤3以后，可以复制直接运行 curl.sh 文件，或者复制 curl.sh 文件内容到控制台/终端/ApiPost中运行");
    }

}
