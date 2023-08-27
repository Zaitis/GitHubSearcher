# GitHub Searcher App

GitHub Searcher App is a Spring Boot application that allows you to retrieve a list of non-fork repositories and their branches for a given GitHub user.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Introduction

GitHub Searcher App is a simple application that leverages the GitHub API to fetch repositories and their branches for a specified GitHub user. It provides a RESTful API endpoint that accepts a GitHub username and returns a list of non-fork repositories with their associated branches.

The project is built using Spring Boot, Apache HttpClient, and Jackson for JSON serialization/deserialization.

## Features

- Retrieve a list of non-fork repositories for a given GitHub user.
- Fetch branches for each repository.

## Getting Started

### Prerequisites

To run this project, you need to have the following installed:

- Java Development Kit 17
- Apache Maven

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/Zaitis/GitHubSearcher.git
   cd github-searcher-app

2. Build the project using Maven:

    ```bash
   mvn clean package
   
## Usage

1. Start the application:

    ```bash
   java -jar target/GitHubSearcherApp-3.1.2.jar

2. Use your API client (Postman, web browser or curl) to make request to the following endpoint:

    ```bash
   GET http://localhost:8080/repos/{username}
   
Replace {username} with the GitHub username you want to serch for.

Example response:

```json
[
    {
        "repositoryName": "BubbleAndBalanceWord",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "385408bd52c63c9686b07cd19ceebd146b42625a"
            }
        ]
    },
    {
        "repositoryName": "BusinessTripReimbursementApp",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "0f71114ed985b887c60926df55823958a1343a76"
            }
        ]
    },
    {
        "repositoryName": "codeforces",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "master",
                "lastCommitSHA": "091814d1664b3f7a3cff571cb407aad92767c7a0"
            }
        ]
    },
    {
        "repositoryName": "GatePass",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "8ebec995316df40272b47ff1e5957eaffcb2d1f0"
            },
            {
                "branchName": "master",
                "lastCommitSHA": "4ea5c769fcb77fa81768a971206f2093bf9367f3"
            }
        ]
    },
    {
        "repositoryName": "GitHubSearcher",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "2c14c1e1e06ff6023617cd19ac425d9d44e73e99"
            }
        ]
    },
    {
        "repositoryName": "horusTest",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "master",
                "lastCommitSHA": "55114147ed28312c37201b59b231e36bcdf044c3"
            }
        ]
    },
    {
        "repositoryName": "ping-pong",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "b64bc9d03de049238ac5124f59e2b1621d8e4aee"
            }
        ]
    },
    {
        "repositoryName": "registerPatients",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "456262e96d7ad66571dd7880a3aafbc584d11e7e"
            }
        ]
    },
    {
        "repositoryName": "shop-backend",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "dff83324d0b1aea92c904e9c17a9854162534518"
            }
        ]
    },
    {
        "repositoryName": "shop-frontend",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "c1e70a063122576bb5cc4f09c2918d25c2431e4b"
            }
        ]
    },
    {
        "repositoryName": "Zaitis",
        "ownerLogin": "Zaitis",
        "branches": [
            {
                "branchName": "main",
                "lastCommitSHA": "83ff6dc0f5aafe2f872501e89e053f1e408c222f"
            }
        ]
    }
]
```
## Project Structure 

The project is organized as follows:

    src/main/java/pl/painm/GitHubSearcherApp/controller: Contains the REST controller.
    src/main/java/pl/painm/GitHubSearcherApp/model: Contains the data models.
    src/main/java/pl/painm/GitHubSearcherApp/service: Contains the business logic and API calls.