// def COLOR_MAP = [
//     'SUCCESS': '#00FF00', // Green
//     'FAILURE': '#FF0000', // Red
//     'UNSTABLE': '#FFFF00', // Yellow
//     'ABORTED': '#808080'   // Gray
// ]
pipeline{
	agent any
	tools{
		jdk 'JDK17'
		maven 'MAVEN3.9'
	}
	environment{
	    SNAP_REPO = 'my_studentsnapshot'
        RELEASE_REPO = "my_student-release"
        CENTRAL_REPO    = 'my_student-maven-central'
        NEXUSIP = '172.31.28.79'
        NEXUSPORT = '8081'
        NEXUS_GRP_REPO = 'my_student-maven-group'
        NEXUS_CREDENTIAL_ID = 'nexuslogin'
	}
	stages {
		stage('Fetch Code') {
			steps {
				git branch: 'main', url: 'https://github.com/Rajikehinde/MyStudent.git'
			}
		}
		stage('Build') {
			steps {
				sh """
                mvn -s settings.xml -DskipTests clean install \
                    -DNEXUSIP=${NEXUSIP} \
                    -DNEXUSPORT=${NEXUSPORT} \
                    -DNEXUS_USER=${NEXUS_USER} \
                    -DNEXUS_PASS=${NEXUS_PASS} \
                    -DSNAP_REPO=${SNAP_REPO} \
                    -DRELEASE_REPO=${RELEASE_REPO} \
                    -DCENTRAL_REPO=${CENTRAL_REPO} \
                    -DNEXUS_GRP_REPO=${NEXUS_GRP_REPO}
                """
			}
// 			post {
// 				success {
// 					echo 'Now Archiving it...'
//                   archiveArtifacts artifacts: '**/target/*.war'
//                }
//             }
		}
// 		stage('Unit Test') {
// 			steps {
// 				sh 'mvn -s settings.xml test'
// 			}
// 		}
// 		stage('Checkstyle Analysis') {
//         			steps {
//         				sh 'mvn -s settings.xml checkstyle:checkstyle'
//         			}
//         		}
		//stage('Deploy') {
		//	steps {
		//		echo 'Deploying...'
		//		// Add your deployment commands here
		//	}
		//}
	}
// 	post {
// 		always {
// 			echo 'Slack Notification.'
// 			slackSend channel: '#jenkins-notification',
// 			    color: COLOR_MAP[currentBuild.currentResult] color: '#FFFF00',
// 			    message: "*${currentBuild.currentResult}:* The Job: ${env.JOB_NAME} with build number: ${env.BUILD_NUMBER} has finished. Please check the details at: ${env.BUILD_URL}")
// 		}
// 	}
}