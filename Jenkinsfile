pipeline{
  agent none

  environment {
    MAJOR_VERSION = 1
    }

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
        sh "if ![ -d 'var/www/html/rectangles/all/${env.BRANCH_NAME}' ]; then /var/www/html/rectangles/all/${env.BRANCH_NAME}; fi"
        sh "cp dist/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar /var/www/html/rectangles/all/${env.BRANCH_NAME}/"
      }
    }

    stage ('running on centos') {
      agent {
        label "apache"
      }

      steps{
        sh "wget http://dv.centos.local/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar"
        sh "java -jar rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar 10 23"
      }
    }

    stage ('promote to green') {
      agent {
        label 'apache'
      }
      when {
          branch 'master'
      }
      steps {
        sh "cp /var/www/html/rectangles/all/${env.BRANCH_NAME}/rectangle_${env.MAJOR_VERSION}.${env.BUILD_NUMBER}.jar /var/www/html/rectangles/green/"
      }
    }

    stage ('Promote dev branch to master') {

      agent {
        label 'apache'
      }

      when {
        branch 'development'
       }

       steps {
            echo "Stash local changes"
            sh "git stash"
            echo "check out development branch"
            sh "git checkout development"
            echo "checkout master branch"
            sh "git pull"
            sh "git checkout master"
            echo "merging dev into master"
            sh "git merge development"
            echo "Push  origin master"
            sh "git push origin master"
            echo "tagging the release"
            sh "git tag rectangle-${env.MAJOR_VERSION}.${env.BUILD_NUMBER}"
       }
    }

  }


}
