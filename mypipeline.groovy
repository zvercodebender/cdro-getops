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

 }