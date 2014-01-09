package br.com.conrado.fcontrol.web.enumeration;

public enum PageView {

    AUTHENTICATION("/public/login"),
    MAIN("/private/main");

    private String page;

    private PageView(String page) {
	this.page = page;
    }
    
    public String getPageView() {
	return page;
    }
}
