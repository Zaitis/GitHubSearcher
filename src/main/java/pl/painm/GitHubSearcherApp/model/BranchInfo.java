package pl.painm.GitHubSearcherApp.model;

import lombok.Data;

@Data
public class BranchInfo {
    private String branchName;
    private String lastCommitSHA;


}