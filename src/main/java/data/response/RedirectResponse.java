package data.response;

import java.io.Serializable;

public class RedirectResponse implements Serializable {

    private static final long serialVersionUID = 8411568168008104137L;
    //redirect url
    private final String redirect;


    public RedirectResponse(String redirect) {
        this.redirect = redirect;
    }


    //for jackson serialization
    public RedirectResponse() {
        this.redirect = null;
    }

    public String getRedirect() {
        return redirect;
    }


}
