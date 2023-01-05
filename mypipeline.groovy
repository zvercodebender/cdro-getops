pipeline 'mypipeline', {

  disableMultipleActiveRuns = '0'
  disableRestart = '0'
  enabled = '1'
  overrideWorkspace = '0'
  pipelineRunNameTemplate = null
  projectName = 'MyProject'
  releaseName = null
  skipStageMode = 'ENABLED'
  templatePipelineName = null
  templatePipelineProjectName = null
  type = null
  workspaceName = null

  formalParameter 'message', defaultValue: 'test', {
    orderIndex = '1'
    required = '1'
    type = 'entry'
  }

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
  }

  stage 'Stage DEV', {
    colorCode = '#289ce1'
    pipelineName = 'mypipeline'
    gate 'PRE', {
      }

    gate 'POST', {
      }

    task 'echo message', {
      actualParameter = [
        'commandToRun': 'echo $[message]',
      ]
      subpluginKey = 'EC-Core'
      subprocedure = 'RunCommand'
      taskType = 'COMMAND'
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
        'pipelineName': 'mypipeline',
      ]
      subprocedure = 'pipelineTrace'
      subproject = 'MyProject'
      taskType = 'PROCEDURE'
    }
  }
}