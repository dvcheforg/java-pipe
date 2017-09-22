pipeline{
  agent none

  options{

    buildDiscarder(logRotator(numToKeepStr:'2', artifactNumToKeepStr:'1'))
  }

  stages{

    stage('Unit tests') {
      agent {
        label 'apache'
      }
      steps {
        sh 'ant -f testbuild.xml -v'
        junit 'reports/result.xml'
      }
    }
    stage('build') {
      agent {
        label 'apache'
      }
      steps {
        sh 'ant -f build.xml -v'
      }
      post {
        success{
           archiveArtifacts artifacts: 'dist/*.jar', fingerprint: true
        }
      }
    }
    stage('deploy') {

      agent {
        label 'apache'
      }

      steps{
        sh "cp dist/rectangle_${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all"
      }
    }

    stage ('running on centos') {
      agent {
        label "CentOS"
      }

      steps{
        sh "wget http://dv.centos.local/rectangles/all/rectangle_${env.BUILD_NUMBER}"
        sh "java -jar rectangle_${env.BUILD_NUMBER}.jar 10 23"
      }
    }

  }


}
