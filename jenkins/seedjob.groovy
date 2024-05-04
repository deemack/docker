#!/usr/bin/env groovy

/**
 * Setup default cicd job from src/cicd/Jenkinsfile folder of gitlab project.
 * Add new repositories to list at the end of the file.
 * @param repo The repository for which jobs are created
 * @param path The path to the Jenkinsfile (defaults to cicd)
 * @return
 */
def createPipelineJob(final String repo, final String path = 'cicd') {

    final String groupName = repo.substring(0, repo.lastIndexOf("/"))
    final String repoName = repo.substring(repo.lastIndexOf("/") + 1, repo.length())
    final String jobName = groupName + "___" + repoName + "___" + path.replace("/", "___")

    pipelineJob(jobName) {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("https://github.com/deemack/" + repo +".git")
                        }

                        branches("*/main")
                    }
                }

                scriptPath("src/${path}/Jenkinsfile")
            }
        }
    }
}

createPipelineJob("deemack/test", "jobs")


//createPipelineJobs("sommerfeld.sebastian/docker-jenkins-pipelines")
createPipelineJobs("deemack/test")
