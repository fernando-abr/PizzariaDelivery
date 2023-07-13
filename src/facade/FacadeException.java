package facade;

public class FacadeException extends Exception {

  public FacadeException(String message) {
    super(message);
  }

  public FacadeException(String message, Throwable e)   {
	  super(message, e);
  }
  
}