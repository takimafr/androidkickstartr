package com.athomas.androidkickstartr.util;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.InitCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class GitHubber {

    private static final String COMMIT_MESSAGE = "AndroidKickstartR generation";

    private String accessToken;
    private GitHubClient gitHubClient;
    private RepositoryService repositoryService;

    public GitHubber(String accessToken) {
        this.accessToken = accessToken;
        gitHubClient = new GitHubClient();
        gitHubClient.setOAuth2Token(accessToken);

        repositoryService = new RepositoryService(gitHubClient);
    }

    public Repository createCommit(File srcFolder, String applicationName) throws IOException, GitAPIException {
        Repository repository = repositoryService.createRepository(new Repository().setName(applicationName));

        String cloneUrl = repository.getCloneUrl();

        InitCommand init = new InitCommand();
        init.setDirectory(srcFolder);
        init.setBare(false);
        Git git = init.call();

        StoredConfig config = git.getRepository().getConfig();
        config.setString("remote", "origin", "url", cloneUrl);
        config.save();

        UsernamePasswordCredentialsProvider user = new UsernamePasswordCredentialsProvider(accessToken, "");
        git.add().addFilepattern(".").call();
        git.commit().setMessage(COMMIT_MESSAGE).call();
        git.push().setCredentialsProvider(user).call();

        return repository;
    }
}
