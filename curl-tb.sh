curl -X POST \
        -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsInNpZ25fdHlwZSI6IlNJR04ifQ.eyJhcGlfa2V5IjoiYjkwMmY4ZjM5NTIzMmMwODBiZjMzNmUyNDJiNjUzNzgiLCJleHAiOjE3MDIzNTE4NzczODcsInRpbWVzdGFtcCI6MTcwMjM1MDA3NzM4OH0.O1KRPCaJpUiAm_F_f-RNGG-CbCAC4uf1lXuMTPImMjo" \
        -H "Content-Type: application/json" \
        -H "User-Agent: Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)" \
        -H "Accept: text/event-stream" \
        -d '{
        "top_p": 0.7,
        "temperature": 0.9,
        "incremental": true,
        "request_id": "df-1696992276607",
        "prompt": [
        {
        "role": "user",
        "content": "你好，请问你是谁！"
        }
        ]
        }' \
  https://open.bigmodel.cn/api/paas/v3/model-api/chatglm_turbo/invoke
