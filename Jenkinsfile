//Slack Notification Integration
def gitName = env.GIT_BRANCH
def jobName = env.JOB_NAME
def branchName = env.BRANCH_NAME
def main_branch = ['main', 'develop']

// Environments Declaration
environment {
  jobName = env.JOB_NAME
  branchName = env.BRANCH_NAME
}

// Successful Build
def buildSuccess = [
  [text: "Blog Post API Build Successful on ${branchName}",
  fallback: "Blog Post API Build Successful on ${branchName}",
  color: "#00FF00"
  ]
]

// Failed Build
def buildError = [
  [text: "Blog Post API Build Failed on ${branchName}",
  fallback: "Task Manager API Build Failed on ${branchName}",
  color: "#FF0000"
  ]
]

pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'chmod +x ./mvnw'
        sh './mvnw wrapper:wrapper'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'chmod +x ./mvnw'
        sh './mvnw test'
      }
    }

//     stage('Build Docker Image') {
//       steps {
//         script {
//           docker.build("blog-post-api")
//         }
//       }
//     }

    stage('Clean WS') {
      steps {
        cleanWs()
      }
    }
  }
}