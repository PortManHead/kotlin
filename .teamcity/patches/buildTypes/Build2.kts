package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, create a buildType with id = 'Build2'
in the root project, and delete the patch script.
*/
create(DslContext.projectId, BuildType({
    id("Build2")
    name = "Build 2"

    vcs {
        root(RelativeId("HttpsGithubComSpringProjectsSpringPetclinicRefsHeadsMaster"))
    }

    steps {
        maven {
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
        powerShell {
            name = "Another Build Step"
            scriptMode = script {
                content = """
                    Get-Date
                    echo ${'$'}env:computername
                    echo "I just modified this build step from the SCM"
                """.trimIndent()
            }
        }
    }

    triggers {
        vcs {
        }
    }
}))

