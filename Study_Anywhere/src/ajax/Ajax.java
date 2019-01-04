package ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Ajax {
	public String getJSON(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
