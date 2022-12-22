pipeline 'mypipeline', {
  projectName = 'MyProject'

  formalParameter 'message', defaultValue: 'test', {
    orderIndex = '1'
    required = '1'
    type = 'entry'
  }

  formalParameter 'ec_stagesToRun', {
    expansionDeferred = '1'
  }

  stage 'Stage 1', {
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