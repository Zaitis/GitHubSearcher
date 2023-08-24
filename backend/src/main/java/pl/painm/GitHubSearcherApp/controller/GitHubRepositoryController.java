package pl.painm.GitHubSearcherApp.controller;

import lombok.RequiredArgsConstructor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.painm.GitHubSearcherApp.service.GitHubRepositoryService;

@RestController
@RequestMapping("/repos")
@RequiredArgsConstructor
public class GitHubRepositoryController {

    private final GitHubRepositoryService service;

    @GetMapping("/{username}")
    public ResponseEntity<Object> listUserRepositories(@PathVariable String username) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        return service.getListUserRepositories(username, httpClient);
    }


}
