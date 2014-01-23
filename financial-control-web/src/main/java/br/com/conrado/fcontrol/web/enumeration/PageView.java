package br.com.conrado.fcontrol.web.enumeration;

public enum PageView {

    AUTHENTICATION("/public/login"),
    MAIN("/private/main"), 
    USER_REGISTER("public/user/register"),
    UNAUTHORIZED_VIEW("/public/unauthorized"), 
    UNAUTHORIZED_INIT_VIEW("/api/unauthorized");

    private String page;

    private PageView(String page) {
	this.page = page;
    }
    
    public String getPageView() {
	return page;
    }
}
