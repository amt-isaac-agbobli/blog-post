//Slack Notification Integration
def gitName = env.GIT_BRANCH
def jobName = env.JOB_NAME
def branchName = env.BRANCH_NAME
def main_branch = ['main', 'develop']

// Environments Declaration
//
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
  fallback: "Blog API Build Failed on ${branchName}",
  color: "#FF0000"
  ]
]

pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
      sh 'mvn clean install -DskipTests'
      }
    }

    stage('Run Tests') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          docker.build("blog-post-api").run("--name blog-post-api -p 8000:8000 -d")
        }
      }
    }

//     stage('Push Docker Image') {
//       steps {
//         script {
//           docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
//             docker.image("blog-post-api").push("${env.BRANCH_NAME}")
//           }
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

