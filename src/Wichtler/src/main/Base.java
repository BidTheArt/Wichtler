//EN: This source-code is licensend under the GNU General Public License, version 2
//DE: Dieser Sourcecode ist unter der GNU General Public Licence, Version 2
//FR: Ce code source est sous licence GNU General Public Licence, version 2

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public abstract class Base {

	protected static int id = 1;
	protected static List<Wichtel> wListe = new ArrayList<Wichtel>();
	protected static Sender sender = null;

	public static String version = "[rc-1.0#13]";

	// ID-Pruefsumme, aktualisiert bei Mixen
	static int ctrId = 0;
	static int ctrIdOfTo = 0;

	public static void createWichtel(String nom, String mail) {
		Wichtel nWichtl = new Wichtel(nom, mail, id);
		for (int i = 0; i < wListe.size(); i++) {
			wListe.get(i).optOfFrom.add(nWichtl.id);
			nWichtl.optOfFrom.add(wListe.get(i).id);
		}

		id++;
		wListe.add(nWichtl);
	}

	public static void removeWichtel(int id) {
		// loesche may-get-from dieses wichtels ueberall
		for (int i = 0; i < wListe.size(); i++) {
			for (int y = 0; y < wListe.get(i).optOfFrom.size(); y++) {
				if (wListe.get(i).optOfFrom.get(y) == id) {
					wListe.get(i).optOfFrom.remove(y);
				}
			}
		}
		// loesche wichtel
		for (int i = 0; i < wListe.size(); i++) {
			Wichtel tmp = wListe.get(i);
			if (tmp.id == id) {
				wListe.remove(i);
				return;
			}
		}
	}

	public static void resetOptions(int id) {
		Wichtel tmp = getWichtel(id);
		for (int i = 0; i < wListe.size(); i++) {
			if (wListe.get(i).id != tmp.id) {
				tmp.optOfFrom.add(wListe.get(i).id);
			}
		}
	}

	public static void removeOption(int idWho, int idOption) {
		Wichtel tmp = getWichtel(idWho);
		for (int i = 0; i < tmp.optOfFrom.size(); i++) {
			if (tmp.optOfFrom.get(i) == idOption) {
				tmp.optOfFrom.remove(i);
			}
		}
	}

	public static Wichtel getWichtel(int id) {
		for (int i = 0; i < wListe.size(); i++) {
			Wichtel tmp = wListe.get(i);
			if (tmp.id == id) {
				return tmp;
			}
		}
		return null;
	}

	public static void setSender(String mailAdress, String username, String password, String host, String port) {
		Sender tmp = new Sender(mailAdress, username, password, host, port);
		sender = tmp;
	}

	public static void mixWichtel() {
		List<Wichtel> wTopf = new ArrayList<>(wListe);
		while (wTopf.size() > 0) {

			// Sortiere Wichtel nach Optionen
			wTopf.sort(Comparator.comparing(Wichtel::getOpts));
			Wichtel tmp = wTopf.get(0);

			// Fixiere durch Zufall eine Option
			Random rnd = new Random();
			int x = rnd.nextInt(tmp.optOfFrom.size());

			// Gib diese Option an den Wichtler
			int idW = tmp.optOfFrom.get(x);
			getWichtel(idW).idOfTo = tmp.id;

			// Loesche diese Option nun bei allen Optionslisten
			for (int i = 0; i < wListe.size(); i++) {
				Wichtel tmp2 = wListe.get(i);
				for (int y = 0; y < tmp2.optOfFrom.size(); y++) {
					if (tmp2.optOfFrom.get(y) == getWichtel(idW).id) {
						tmp2.optOfFrom.remove(y);
					}
				}
			}
			// Entferne nun den fertigen Wichtel aus dem Topf
			wTopf.remove(0);
		}
		// Kontrolle:
		for (int i = 0; i < wListe.size(); i++) {
			ctrId += wListe.get(i).id;
			ctrIdOfTo += wListe.get(i).idOfTo;
		}
	}

	public static void sendMail() {
		for (int i = 0; i < wListe.size(); i++) {
			Wichtelmail mail = new Wichtelmail(sender, wListe.get(i).name, getWichtel(wListe.get(i).idOfTo).name,
					wListe.get(i).mail);
			mail.sendMail();
		}
	}
}
