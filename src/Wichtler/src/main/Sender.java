//EN: This source-code is licensend under the GNU General Public License, version 2
//DE: Dieser Sourcecode ist unter der GNU General Public Licence, Version 2
//FR: Ce code source est sous licence GNU General Public Licence, version 2

package main;

public class Sender {
	
    private String mailAdress;
    private String username;
    private String password;

    private String host;
    private String port; 
    private String auth = "true";
    private String startTLS = "true";
	
	public Sender(String mailAdress, String username, String password, String host, String port) {
		this.mailAdress = mailAdress;
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	protected String getMailAdress() {
		return mailAdress;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

	protected String getHost() {
		return host;
	}

	protected String getPort() {
		return port;
	}

	protected String getAuth() {
		return auth;
	}

	protected String getStartTLS() {
		return startTLS;
	}

}
