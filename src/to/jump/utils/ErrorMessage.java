package to.jump.utils;

public class ErrorMessage {
	private String message;
	private boolean shown = false;
	
	public ErrorMessage(String msg) {
		this.message = msg;
	}
	
	public boolean notShown() {
		if(!shown) {
			shown = true;
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return message;
	}
}
