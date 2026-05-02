pipeline {
    agent any
    environment {
        JAVA_HOME = "/opt/jdks/jdk-25"
    }
    stages {
        stage('Checkout') { 
            steps {
                sh 'echo checkout'
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'GitHub-romiiw', url: 'https://github.com/romiiw/tdd_bank_account']])
            }
        }
        stage('Build') { 
            steps {
                sh 'echo build'
                dir('backend') {
                    sh 'chmod +x ./gradlew'
                    sh './gradlew test'    
                }
                recordCoverage(tools: [[parser: 'JACOCO',pattern: '**/jacocoTestReport.xml']])
                junit stdioRetention: '', testResults: '**/test-results/test/*.xml'
                nodejs('24.11.1') {
                    dir('frontend') {
                        sh 'npm install'
                        sh 'npm run lint:html'                    
                    }
                }
                withCredentials([string(credentialsId: 'sonar-key-backend', variable: 'TOKEN')]) {
                    dir('backend') {
                        sh "./gradlew sonar -Dsonar.projectKey=bank_account_backend1 -Dsonar.projectName=\'bank_account_backend1\' -Dsonar.host.url=http://sonarqube:9000 -Dsonar.token=$TOKEN"    
                    }                    
                }
                withCredentials([string(credentialsId: 'sonar-key-frontend', variable: 'TOKEN')]) {
                    dir('frontend') {
                        nodejs('24.11.1') {
                            sh "npx sonar-scanner -Dsonar.host.url=http://sonarqube:9000 -Dsonar.projectKey=bank_account_frontend -Dsonar.projectName=\'bank_account_frontend\' -Dsonar.token=$TOKEN"  
                        }
                    }                    
                }
            }
        }
        stage('Docker') {
            steps {
                sh '''
                    export DOCKER_HOST=tcp://172.17.0.2:2375
                    docker build -t mosazhaw/devopsdemo .
                '''
            }
        }
    }
}

