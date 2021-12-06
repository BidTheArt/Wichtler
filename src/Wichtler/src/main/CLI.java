//EN: This source-code is licensend under the GNU General Public License, version 2
//DE: Dieser Sourcecode ist unter der GNU General Public Licence, Version 2
//FR: Ce code source est sous licence GNU General Public Licence, version 2

//This CLI is for now more for test&fun, not really for real use;

package main;

import java.util.Scanner;

public class CLI extends Base {

	// Das ist die CLI-Anwendung

	public static void main(String[] args) {
		System.out.println("Wichtler " + version + " [CLI] - Copyright (C) Bid 2021" + '\r' + '\n'
				+ "\nDieses Programm ist FREIE SOFTWARE unter der GPLv2-Lizenz!\r\nEs gibt KEINERLEI Gewaehrleistung!\r\n");
		bonjour();

		System.out.println("*** WICHTLER BEENDET ***");
	}

	public static void bonjour() {

		System.out.println("- - - - - - - - - - - - - - - -" + '\r' + '\n' + "AUSWAHL EINGEBEN: " + '\r' + '\n'
				+ "- - - - - - - - - - - - - - - -" + '\r' + '\n' + "-NB (1) | Neuer Wichtel" + '\r' + '\n'
				+ "-LS (2) | Zeige alle Wichtel" + '\r' + '\n' + "-ED (3) | Wichtel editieren" + '\r' + '\n'
				+ "-MS (4) | Mischen & Senden" + '\r' + '\n' + "-PO (5) | Sendermail festlegen" + '\r' + '\n'
				+ "-ST (6) | Status" + '\r' + '\n' + "-IN (7) | Ueber Wichtler" + '\r' + '\n' + "-Q  (0) | Beenden"
				+ '\r' + '\n' + "- - - - - - - - - - - - - - - -");

		String name = readLine();
		if (name.equals("NB") || name.equals("nb") || name.equals("1")) {
			clear();
			nbWichtel();
		} else if (name.equals("LS") || name.equals("ls") || name.equals("2")) {
			clear();
			lsWichtel();
		} else if (name.equals("ED") || name.equals("ed") || name.equals("3")) {
			clear();
			edWichtel();
		} else if (name.equals("MS") || name.equals("ms") || name.equals("4")) {
			clear();
			msWichtel();
		} else if (name.equals("PO") || name.equals("po") || name.equals("5")) {
			clear();
			poWichtel();
		} else if (name.equals("ST") || name.equals("st") || name.equals("6")) {
			clear();
			stWichtel();
		} else if (name.equals("IN") || name.equals("in") || name.equals("7")) {
			clear();
			inWichtel();
		} else if (name.equals("Q") || name.equals("q") || name.equals("0")) {
			clear();
			System.out.println("Wichtler wird beendet...\r\n");
			printAsciiArt();
			return;
		} else {
			wrongInput();
		}
	}

	public static void nbWichtel() {
		System.out.println("- - - - - - - - - - - - - - -" + "\r\nHINZUFUEGEN:" + "\r\n- - - - - - - - - - - - - - -");
		System.out.println("Namen eingeben:");
		String nom = readLine();
		System.out.println("E-Mail eingeben:");
		String mail = readLine();
		createWichtel(nom, mail);
		goBack("HINZUFUEGEN");
	}

	public static void lsWichtel() {
		System.out.println("\r\n- - - - - - - - - - - - - - - - - - - - - - - -"
				+ "\r\n[ID]  |  [NAME]  |  [MAIL]  |  [MAY GET-FROM]"
				+ "\r\n- - - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println();
		listWichtels();
		goBack("LISTE");
	}

	public static void edWichtel() {
		System.out.println(
				"- - - - - - - - - - - - - - -\r\n" + "WICHTEL BEARBEITEN\r\n" + "- - - - - - - - - - - - - - -");
		System.out.println("Wichtelliste: \r\n");
		listWichtels();
		System.out.println("\r\nGib die ID des Wichtels an,\r\nWelchen du bearbeiten moechtest: ");
		String id = readLine();
		if (isInt(id) == false) {
			wrongInput();
		}
		Wichtel toEdit = getWichtel(Integer.parseInt(id));
		clear();
		System.out.println("- - - - - - - - - - - - - - - - - - -\r\n" + "AUSWAHL EINGEBEN:\r\n"
				+ "- - - - - - - - - - - - - - - - - - -\r\n" + "-RM (1) | Loeschen\r\n"
				+ "-RO (2) | Entferne May-Get-From\r\n" + "-SM (3) | Setzte May-Get-From zurueck\r\n"
				+ "-CN (4) | Aendere Namen\r\n" + "-CM (5) | Aendere Mail-Adresse\r\n"
				+ "- - - - - - - - - - - - - - - - - - -\r\n" + "Gewaehlter Wichtel: " + toEdit.name + "\r\n"
				+ "- - - - - - - - - - - - - - - - - - -");
		String name = readLine();
		if (name.equals("RM") || name.equals("rm") || name.equals("1")) {
			// loeschen
			removeWichtel(toEdit.id);
			goBack("LOESCHEN");
		} else if (name.equals("RO") || name.equals("ro") || name.equals("2")) {
			// einschraenken may-get
			System.out.println("Von wem soll " + toEdit.name + " (" + toEdit.id + ") " + "nichts bekommen koennen?");
			System.out.println("Wichtelliste: \r\n");
			listWichtels();
			System.out.println("Gib eine Id ein: ");
			String id2 = readLine();
			if (isInt(id2) == false) {
				wrongInput();
			}
			removeOption(toEdit.id, Integer.parseInt(id2));
			goBack("EINSCHRAENKEN");
		} else if (name.equals("SM") || name.equals("sm") || name.equals("3")) {
			resetOptions(toEdit.id);
			goBack("RUECKSETZUNG");
		} else if (name.equals("CN") || name.equals("cn") || name.equals("4")) {
			System.out.println("Gib einen neuen Namen fuer" + toEdit.name + " ein:");
			toEdit.name = readLine();
			goBack("NAMENSAENDERUNG");
		} else if (name.equals("CM") || name.equals("cm") || name.equals("5")) {
			System.out.println("Gib einen neue Mailadresse fuer" + toEdit.name + ", " + toEdit.mail + "ein:");
			toEdit.mail = readLine();
			goBack("MAILAENDERUNG");
		} else {
			wrongInput();
		}
	}

	public static void msWichtel() {
		if (sender == null) {
			System.out.println("Kein Sender festgelegt!");
			goBack("MISCHEN & SENDEN");
		}
		mixWichtel();
		System.out.println(
				"- - - - - - - - - - - - - - -" + "\r\n" + "MISCHEN & SENDEN:" + "\r\n- - - - - - - - - - - - - - -");
		System.out.println("\r\n" + ctrId + " - " + ctrIdOfTo);
		if (ctrId == ctrIdOfTo && ctrId > 0 && ctrIdOfTo > 0) {
			System.out.println("-> Die Pruefsummen stimmen. Sieht gut aus!");
			sendMail();
		} else {
			System.out.println("\r\n/!\\ Die Pruefsummen stimmen nicht. \r\nDie Anzahl war eventuell unausgewogen");
		}
		System.out.println(
				"Mit Mischen & Senden werden die MayGetFrom-Optionen geloescht.\r\nFalls nötig kannst du sie unter Edit wiederherstellen.");
		goBack("MISCHEN & SENDEN");
	}

	public static void poWichtel() {
		System.out.println(
				"- - - - - - - - - - - - - - -" + "\r\n" + "SENDEROPTIONEN:" + "\r\n- - - - - - - - - - - - - - -");
		System.out.println("Neue Absenderadresse:");
		String oAddr = readLine();
		if (oAddr == "" || oAddr == null) {
			wrongInput();
		}
		System.out.println("Username der Absenderadresse:");
		String oUsr = readLine();
		if (oUsr == "" || oUsr == null) {
			wrongInput();
		}
		System.out.println("Passwort der Absenderadresse:");
		String oPwd = readLine();
		if (oPwd == "" || oPwd == null) {
			wrongInput();
		}
		System.out.println("SMTP-Server der Adresse:");
		String oSrv = readLine();
		if (oSrv == "" || oSrv == null) {
			wrongInput();
		}
		System.out.println("SMTP-Port der Adresse:");
		String oPrt = readLine();
		if (oPrt == "" || oPrt == null) {
			wrongInput();
		}
		setSender(oAddr, oUsr, oPwd, oSrv, oPrt);
		goBack("SENDEROPTIONEN");
	}

	public static void stWichtel() {
		System.out
				.println("- - - - - - - - - - - - - - -" + "\r\nStatus Wichtel:" + "\r\n- - - - - - - - - - - - - - -");
		System.out.println("Anzahl Wichtel: " + wListe.size());

		System.out.println(
				"\r\n- - - - - - - - - - - - - - -" + "\r\nStatus Sender:" + "\r\n- - - - - - - - - - - - - - -");

		if (sender != null) {
			System.out.println("Sendermail:" + '\r' + '\n' + sender.getMailAdress());
			System.out.println("Username:" + '\r' + '\n' + sender.getUsername());
			System.out.println("SMTP-Server:" + '\r' + '\n' + sender.getHost());
			System.out.println("SMTP-Port:" + '\r' + '\n' + sender.getPort());
			System.out.println("Auth:" + '\r' + '\n' + sender.getAuth());
			System.out.println("Start-TLS:" + '\r' + '\n' + sender.getStartTLS());
		} else {
			System.out.println("Kein Sender festgelegt!");
		}
		goBack("STATUS");
	}

	public static void inWichtel() {
		System.out.println("- - - - - - - - - - - - - - -" + "\r\nUEBER:" + "\r\n- - - - - - - - - - - - - - -");
		System.out.println("\r\nVersion: " + version);
		System.out.println("Lizenz: " + "GNU Public Licence 2");
		System.out.println("Lizenzinformation: " + "https://www.gnu.org/licenses/old-licenses/gpl-2.0.html");
		System.out.println("Source-Code: " + "-GITHUBLINK COMING SOON-");
		System.out.println("(C) Bid 2021");
		printAsciiArt();
		goBack("UEBER");
	}

	public static String readLine() {
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}

	public static boolean isInt(String value) {
		try {
			int number = Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void goBack(String vonWo) {

		System.out.println("\r\n*** " + vonWo + " BEENDET" + " ***");
		System.out.println("Zurueck mit [ENTER]");
		Scanner in = new Scanner(System.in);
		if (in.nextLine() == "") {
			clear();
			bonjour();
		} else {
			wrongInput();
		}
	}

	public static void wrongInput() {
		System.out.println("--Ungueltige Eingabe--");
		System.out.println("Press [ENTER]");
		Scanner in = new Scanner(System.in);
		if (in.nextLine() == "") {
			clear();
			bonjour();
			return;
		} else {
			wrongInput();
			return;
		}
	}

	public static void listWichtels() {
		for (int i = 0; i < wListe.size(); i++) {
			Wichtel tmp = wListe.get(i);
			String optionen = "";
			for (int y = 0; y < tmp.optOfFrom.size(); y++) {
				optionen = String.valueOf(optionen) + " " + tmp.optOfFrom.get(y);
			}
			System.out.println(tmp.id + " --- " + tmp.name + " --- " + tmp.mail + " ---" + optionen);
		}
	}

	public static void clear() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}

	public static void printAsciiArt() {
		System.out.println("          (+)\n"
				+ "          /=\\\n"
				+ "         / + \\\n"
				+ "        /=-*-=\\\n"
				+ "        // i \\\\\n"
				+ "       / o ° o \\ \n"
				+ "      /=*=-*-=-*\\\n"
				+ "      // + ° + \\\\\n"
				+ "     / o  \"i\"  O \\\n"
				+ "    /=*=-=-*-=-=*=\\\n"
				+ "    // °  \"i\"  ° \\\\ \n"
				+ "   / +   o ° o   + \\\n"
				+ "  /=*=-=*=-*-=*=-=*=\\\n"
				+ "         |-|~|\n"
				+ "  _.~^~._|~|-|_.~^~._\n"
				+ "========================\n"
				+ "FROHE WEIHNACHTEN\n"
				+ "wuenscht der Wichtler\n"
				+ "========================");
	}
}
