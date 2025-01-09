package com.parch.combine.gitlab.helper;

import com.parch.combine.gitlab.base.auth.login.GitLabAuthLoginLoginConfig;
import com.parch.combine.gitlab.base.auth.token.GitlabTokenConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * API链接对象缓冲池
 *
 * @author parch
 * @Date 2025/1/9
 */
public class GitlabApiCache {

    private final static Map<String, GitLabApi> CACHE = new HashMap<>();

    public synchronized static GitLabApi register(GitLabAuthLoginLoginConfig config) throws GitLabApiException {
        GitLabApi api = GitLabApi.oauth2Login(config.url(), config.username(), config.password(), config.secretToken(), config.clientConfig(), config.ignoreCertificateErrors());
        CACHE.put(config.key(), api);
        return api;
    }

    public synchronized static GitLabApi register(GitlabTokenConfig config) {
        GitLabApi api = new GitLabApi(config.url(), config.accessToken(), config.secretToken(), config.clientConfig());
        CACHE.put(config.key(), api);
        return api;
    }

    public static GitLabApi get(String key) {
        return CACHE.get(key);
    }

    public synchronized static GitLabApi clear(String key) {
        return CACHE.remove(key);
    }
}
