#!/usr/bin/env groovy
import groovy.io.FileType

/**
 * Setup jobs from gitlab project which contains multiple Jenkinsfiles in different repositories.
 * Add new repositories to list at the end of the file.
 * @param repo
 * @return
 */
def createPipelineJobs(final String repo) {

    final String tempDir="/var/jenkins_home/tmp/repos"
    final String groupName = repo.substring(0, repo.lastIndexOf("/"))
    final String repoName = repo.substring(repo.lastIndexOf("/") + 1, repo.length())

    println "[INFO] Clone repo '${repo}' and checkout branch 'main'"
    ["git", "clone", "git@gitlab.com:${repo}.git"].execute()
    ["git", "checkout", "main"].execute()

    println "[INFO] Wait 30 seconds for git clone to finish"
    sleep(30000)

    // Iterate folders containing the Jenkinsfiles and create pipeline jobs
    new File('docker-jenkins-pipelines/src/main/jobs').eachFile FileType.DIRECTORIES, {
        String folderName = it.name
        String jobName = groupName + "___" + repoName + "___" + folderName

        pipelineJob(jobName) {
            definition {
                cpsScm {
                    scm {
                        git {
                            remote {
                                url("git@gitlab.com:" + repo +".git")
                            }

                            branches("*/main")
                            //branches("*/feat*")
                        }
                    }

                    scriptPath("src/main/jobs/${folderName}/Jenkinsfile")
                }
            }
        }

    }

}

//createPipelineJobs("sommerfeld.sebastian/docker-jenkins-pipelines")
createPipelineJobs("deemack/test")
