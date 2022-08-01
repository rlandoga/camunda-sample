package sample.camunda;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CamundaDelegateExample implements JavaDelegate {


  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {

    //Gets the variable we sent in from the Controller
    String input = (String) delegateExecution.getVariable("userInput");

    //Sets the cellInput variable to the input from the controller in order to run through the decision table
    delegateExecution.setVariable("cellInput", input);

  }

}
