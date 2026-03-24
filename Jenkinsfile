pipeline {
    parameters {
        choice(name: 'OPTION', choices: ['1', '2', '3', '4'], description: 'Select Menu Option')
        string(name: 'SEAT', defaultValue: '0', description: 'Enter Seat Number (For Option 2)')
        string(name: 'NAME', defaultValue: 'Guest', description: 'Enter Name (For Option 2)')
        string(name: 'DEST', defaultValue: 'Pune', description: 'Enter Destination (For Option 2)')
    }
    agent any
    tools {
        ant "Ant_1.10.15"
    }
    
    stages {
        stage("Source") {
            steps {
                git branch: 'main', url: 'https://github.com/samruddhij07/testing'
            }
        }
        
        stage("Build & Compile") {
            steps {
                bat 'ant -f trainticket.xml compile'
            }
        }
        
        stage('Create Artifact') {
            steps {
                bat 'ant -f trainticket.xml jar'
            }
        }
        
        stage('Archive Results') {
            steps {
                archiveArtifacts artifacts: '**/*.jar'
            }
        }
        stage('Run Application') {
            steps {
                script{
                    def inputContent = ""
                    
                    if (params.OPTION=='2'){
                        inputContent = "${params.OPTION}\n${params.SEAT}\n${params.NAME}\n${params.DEST}\n4\n"
                    } 
                    else if (params.OPTION =='4') {
                        inputContent = "4\n"
                    }
                    else{
                        inputContent = "${params.OPTION}\n4\n"
                    }
                    writeFile file: 'input.txt', text: inputContent
                    bat 'ant -f trainticket.xml run < input.txt'
                }
            }
        }
    }
    
    post {
        success {
            echo 'Train Ticket build successful! Your JAR is ready.'
        }
        failure {
            echo 'Build failed. Check the Console Output for Ant errors.'
        }
    }
}
