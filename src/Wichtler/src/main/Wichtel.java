//EN: This source-code is licensend under the GNU General Public License, version 2
//DE: Dieser Sourcecode ist unter der GNU General Public Licence, Version 2
//FR: Ce code source est sous licence GNU General Public Licence, version 2

package main;

import java.util.ArrayList;
import java.util.List;

public class Wichtel {
	
	int id;
	String name;
	String mail;
	List<Integer> optOfFrom = new ArrayList<Integer>();
	
	int idOfTo;

	public Wichtel(String name, String mail, int id) {
		this.name = name;
		this.mail = mail;
		this.id = id;
	}
	
	public int getOpts() {
		return optOfFrom.size();
	}

}
