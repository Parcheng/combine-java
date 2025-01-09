package com.parch.combine.gitlab.base;

import com.parch.combine.core.component.base.AbstractComponent;

/**
 * 邮件组件
 */
public abstract class AbstractGitlabComponent<T extends GitlabInitConfig, R extends GitlabLogicConfig> extends AbstractComponent<T, R> {

    /**
     * 构造器
     */
    public AbstractGitlabComponent(Class<T> tClass, Class<R> rClass) {
        super(tClass, rClass);
    }

//    protected ComponentDataResult execute() {
//
//    }

//    GitLabApi api = new GitLabApi("https://git.17track.net:8099", "z8DbQ3rxrcs_FpD4Angk", null, null);
//    List<Project> list = api.getProjectApi().getProjects();
//        System.out.println(list.size());
}
