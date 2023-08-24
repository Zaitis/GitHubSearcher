package pl.painm.GitHubSearcherApp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.painm.GitHubSearcherApp.model.BranchInfo;
import pl.painm.GitHubSearcherApp.model.GitHubRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GitHubRepositoryService {


    public ResponseEntity<Object> getListUserRepositories(String username, CloseableHttpClient httpClient) {
        HttpGet getRequest = new HttpGet("https://api.github.com/users/" + username + "/repos");
        getRequest.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
            if (response.getStatusLine().getStatusCode() == HttpStatus.NOT_FOUND.value()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(createErrorResponse(HttpStatus.NOT_FOUND, "User not found"));
            }

            List<GitHubRepository> repositories = processResponse(response);
            return ResponseEntity.ok(repositories);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
        }
    }

    private List<GitHubRepository> processResponse(CloseableHttpResponse response) throws IOException {
        List<GitHubRepository> repositories = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonResponse = objectMapper.readTree(response.getEntity().getContent());
        for (JsonNode repoNode : jsonResponse) {
            if (!repoNode.get("fork").asBoolean()) {
                GitHubRepository repository = new GitHubRepository();
                repository.setRepositoryName(repoNode.get("name").asText());
                repository.setOwnerLogin(repoNode.get("owner").get("login").asText());
                repository.setBranches(getRepositoryBranches(repository.getOwnerLogin(), repository.getRepositoryName()));
                repositories.add(repository);
            }
        }

        return repositories;
    }

    private List<BranchInfo> getRepositoryBranches(String owner, String repoName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet("https://api.github.com/repos/" + owner + "/" + repoName + "/branches");
        getRequest.addHeader("Accept", "application/json");

        try (CloseableHttpResponse response = httpClient.execute(getRequest)) {
            List<BranchInfo> branches = new ArrayList<>();
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode jsonResponse = objectMapper.readTree(response.getEntity().getContent());
            for (JsonNode branchNode : jsonResponse) {
                BranchInfo branch = new BranchInfo();
                branch.setBranchName(branchNode.get("name").asText());
                branch.setLastCommitSHA(branchNode.get("commit").get("sha").asText());
                branches.add(branch);
            }

            return branches;
        }
    }

    private Object createErrorResponse(HttpStatus status, String message) {
        return Map.of(
                "status", status.value(),
                "Message", message
        );
    }
}
