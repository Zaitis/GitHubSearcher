package pl.painm.GitHubSearcherApp.model;

import lombok.Data;

import java.util.List;

@Data
public class GitHubRepository {
    private String repositoryName;
    private String ownerLogin;
    private List<BranchInfo> branches;
}