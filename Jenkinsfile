node {

    stage('git clone'){
      echo 'git clone starts';
      checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '40193612-4313-4def-a47d-075fa7bff27e', url: 'http://github.com/ShunyiChen/codetest-getcurrentweather.git']]])
      echo 'git clone completed';
    }

    stage('build'){
     echo 'build starts';
     env.JAVA_HOME="${tool 'jdk1.8.0_251'}"
        withMaven(
         maven: 'M3',
         mavenLocalRepo: '.repository') {
             sh "mvn clean install -U  -P${profile} -Dmaven.test.skip=true"
     }
     echo 'build completed';
    }
    
    stage('test'){
     echo 'test skipped';
    }
    
    stage('deploy'){
     echo 'deploy starts';
      sshagent(credentials: ['deploy_ssh_key']) {
         sh 'sh deploy.sh'//待续。。。
      }
      echo 'deploy completed';
    }

}