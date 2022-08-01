package sample.camunda;

import java.util.HashMap;
import java.util.Map;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.rest.dto.VariableValueDto;
import org.camunda.bpm.engine.runtime.ProcessInstanceWithVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamundaInputController {

  //Used to start Camunda processes
  private RuntimeService runtimeService;
  //Entity for what is being passed into Camunda
  private final CamundaInput camundaInput = new CamundaInput();

  @Autowired
  public void PayloadRequest(RuntimeService runtimeService) {
    this.runtimeService = runtimeService;
  }

  @PostMapping("/test")
  public Boolean getBooleanFromInput(@RequestBody String userInput) {
    //Maps entity to what is sent to the Endpoint
    camundaInput.setUserInput(userInput);

    //Creates Variables object to pass into Camunda
    Map<String, Object> variables = createVariables(camundaInput);

    //Creates an instance object that runs the process based on the Key, adds the Variables object for what is needed in the process,
    // and returns all Variables created and updated in the process
    ProcessInstanceWithVariables instance = runtimeService.createProcessInstanceByKey("sample-camunda-process").setVariables(variables).executeWithVariablesInReturn();

    //Creates a map of the Variables from Camunda
    Map<String, Object> result = instance.getVariables();

    //Returns the specific variable that is created through the Decision table in Camunda as a test
    return (Boolean) result.get("bool");

  }

  //Creates Variables object that is then passed into Camunda proper
  private Map<String, Object> createVariables(CamundaInput input) {
    Map<String, Object> variables = new HashMap<>();
    variables.put("userInput", input.getUserInput());

    return variables;
  }
}
