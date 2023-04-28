package gr.aueb.cf.schoolapp.authentication;

import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static final String REDIRECT_URL_SECTION_ATTRIBUTE_NAME = "REDIRECT_URL";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{

        Object redirectURLObject = request.getSession().getAttribute(REDIRECT_URL_SECTION_ATTRIBUTE_NAME);

        if (redirectURLObject != null) {
            setDefaultTargetUrl(redirectURLObject.toString());
        }else {
            setDefaultTargetUrl("/api/teachers?lastname=");
        }

        request.getSession().removeAttribute(REDIRECT_URL_SECTION_ATTRIBUTE_NAME);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
