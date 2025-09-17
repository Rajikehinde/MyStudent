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
        NEXUS_CREDENTIAL_ID = 'Nexuslogin'
	}
	stages {
		stage('Fetch Code') {
			steps {
				git branch: 'main', url: 'https://github.com/Rajikehinde/MyStudent.git'
			}
		}
		stage('Build') {
			steps {
				sh 'mvn -s settings.xml clean install -DskipTests'
			}
// 			post {
// 				success {
// 					echo 'Now Archiving it...'
//                   archiveArtifacts artifacts: '**/target/*.war'
//                }
//             }
		}
		stage('Deploy to Nexus'){
		    steps {
		        withCredentials([usernamePassword(credentialsId: "${NEXUS_CREDENTIAL_ID}", usernameVariable: 'NEXUS_USER', passwordVariable: 'NEXUS_PASS')]){
		            sh """
		            mvn -s settings.xml deploy \
		             -DskipTests -Dnexus.username=${NEXUS_USER} -Dnexus.password=${NEXUS_PASS} \
		            -DaltDeploymentRepository=my_studentsnapshot::default::http://${NEXUSIP}:${NEXUSPORT}/repository/my_studentsnapshot/ \
		            -DaltReleaseDeploymentRepository=my_student-release::default::http://${NEXUSIP}:${NEXUSPORT}/repository/my_student-release/
		            """
		        }
		    }
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