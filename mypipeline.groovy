pipeline 'My Pipeline Trigger Demo', {
  projectName = 'MyProject'

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
  }

  stage 'Stage 1', {
    colorCode = '#289ce1'
    pipelineName = 'My Pipeline Trigger Demo'
    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'Started from Trigger', {
      actualParameter = [
        'commandToRun': 'echo "Hello I was started from a trigger"',
      ]
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
    }

    task 'get Properties', {
      actualParameter = [
        'basePath': 'myPipeline',
        'pipelineName': 'My Pipeline Trigger Demo',
      ]
      subprocedure = 'pipelineTrace'
      subproject = 'MyProject'
      taskType = 'PROCEDURE'
    }

    task 'Update Pipeline Name', {
      actualParameter = [
        'commandToRun': '''import groovy.json.*
import com.electriccloud.client.groovy.ElectricFlow
import com.electriccloud.client.groovy.models.*
  
  
/***************************************************************************************/
def printProperties( path ) {
	ElectricFlow ef = new ElectricFlow()
	PropertyStucture = ef.getProperties(path: path, recurse: true, expand: false).propertySheet
    try {
      propList = PropertyStucture.property
      for (myProperty in propList) {
          if ( myProperty.propertySheet != null ) {
              //println ">>> property sheet " + myProperty.propertyName
              printProperties( path + "/" + myProperty.propertyName )
              //println "<<< property sheet " + myProperty.propertyName
          } else {
              println path + "/" + myProperty.propertyName + " = " + myProperty.value
          }
      }
    } catch( Exception ex ) {
    	println myProperty.propertyName + " = NOT FOUND"
    }
}

ElectricFlow ef = new ElectricFlow()
path = \'/myPipelineRuntime/parsedWebhookData\'
printProperties( path )''',
        'shellToUse': 'ec-groovy',
      ]
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
    }
  }

  trigger 'GitHub Trigger', {
    accessTokenPublicId = 'fjmybkxghbc229qr8k1mgnu0sn9k1q'
    actualParameter = [
      'ec_stagesToRun': '["Stage 1"]',
    ]
    pipelineName = 'My Pipeline Trigger Demo'
    pluginKey = 'EC-Github'
    pluginParameter = [
      'commitStatusEvent': 'false',
      'includeBranches': 'cloudbees',
      'includeCommitStatuses': 'success',
      'includePrActions': 'closed_merged',
      'prEvent': 'false',
      'pushEvent': 'true',
      'repositories': 'zvercodebender/hello-python',
    ]
    quietTimeMinutes = '0'
    runDuplicates = '0'
    serviceAccountName = 'serviceAccount'
    triggerType = 'webhook'
    webhookName = 'default'

    // Custom properties

    property 'ec_trigger_state', {
      propertyType = 'sheet'
    }
    ec_lastResponseMessage = 'Launching trigger \'GitHub Trigger\' for event \'push\' in branch(es) \'cloudbees\' of the \'zvercodebender/hello-python\' repository.'
    ec_lastResponseTime = '1671722323493'
  }

  // Custom properties

  property 'ec_counters', {

    // Custom properties
    pipelineCounter = '13'
  }
}