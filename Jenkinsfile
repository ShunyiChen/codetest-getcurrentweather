node {

    stage('git clone'){
      echo 'git clone starts';
      checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '40193612-4313-4def-a47d-075fa7bff27e', url: 'http://github.com/ShunyiChen/codetest-getcurrentweather.git']]])
      echo 'git clone completed';
    }

    stage('build'){
     echo 'build';
    }
    
    stage('test'){
     echo 'test';
    }
    
    stage('deploy'){
     echo 'deploy';
    }
}