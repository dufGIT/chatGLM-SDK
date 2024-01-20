package cn.bugstack.chatglm.common;

/**
 * @Author df
 * @Description: 通用类
 * @Date 2023/10/13 18:39
 */
public class Constants {

    public final static String NULL = null;

    /**
     * 官网支持的请求角色类型；system、user、assistant
     * https://platform.openai.com/docs/guides/chat/introduction
     */
    public enum Role {
        USER("uer"),
        ASSISTANT("assistant");

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
