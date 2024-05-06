node('master') {
       
    stage('SCM Checkout stage') {
           
                 git 'https://github.com/jayalakshmi.nachireddi/EdisonLearningLearnNow.git'   
 
               }        
        
        stage('Clean & Compile Stage') {
            
                    bat label: '', script: 'mvn clean compile'                
                   
               }
               
               stage('Testing Stage') {
            
                   bat label: '', script: 'mvn test'
               
               }
               
              // stage('Deployment Stage') {
            
                 //  bat label: '', script: 'mvn deploy'
                                  
              // }
               
                stage('Reports Stage') {
            
                    cucumber failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
                                    
               }
               
}

