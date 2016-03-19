package activiti.spring.javnaNabavka.deploy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;

/**
 * Ucitava korisnike i grupe iz yml fajlova
 * Vise o koriscenoj biblioteci: http://yamlbeans.sourceforge.net/
 * @author xyz
 *
 */
public class DataInit {

	private static final String groupsPath ="./src/main/resources/properties/groups.yml";
	private static final String usersPath ="./src/main/resources/properties/users.yml";
	private static ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	private static IdentityService identityService = processEngine.getIdentityService(); 
	private static Scanner scanner;


	/**
	 * Kreira grupe na osnovu podataka iz yml fajla 
	 */
	@SuppressWarnings("rawtypes")
	private static void initGroupsYml(){
		YamlReader reader = null;
		Map map;
		Group newGroup;
		try {
			reader = new YamlReader(new FileReader(groupsPath));
			while (true) {
				map = (Map) reader.read();
				if (map == null)
					break;
				newGroup = identityService.newGroup((String) map.get("id"));
				newGroup.setName((String) map.get("name"));
				newGroup.setType((String) map.get("type"));
				identityService.saveGroup(newGroup);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (YamlException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Kreira korisnike i clanstva grupama na osnovu podataka iz yml fajla 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void initUsersYml(){
		YamlReader reader = null;
		Map map;
		User newUser;
		try {
			reader = new YamlReader(new FileReader(usersPath));
			while (true) {
				map = (Map) reader.read();
				if (map == null)
					break;
				newUser = identityService.newUser((String) map.get("id"));
				newUser.setFirstName((String) map.get("firstName"));
				newUser.setLastName((String) map.get("lastName"));
				newUser.setEmail((String) map.get("email"));
				newUser.setPassword((String) map.get("password"));
				identityService.saveUser(newUser);
				
				for (HashMap recordMap : (List<HashMap>) map.get("groups"))
					identityService.createMembership(newUser.getId(),(String) recordMap.get("id"));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (YamlException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args){
		List<Group> groups = identityService.createGroupQuery().list();
		List<User> users = identityService.createUserQuery().list();
		
		boolean exists = groups.size() != 0 || users.size() != 0;
		
		if (exists) {
			scanner = new Scanner(System.in);
			System.out.println("Obrisi postojece korisnike i grupe? [d/n]");
			Character ans = scanner.next().charAt(0);
			
			if (ans == 'd') {
				for (User u: users)
					identityService.deleteUser(u.getId());
				
				for (Group g: groups)
					identityService.deleteGroup(g.getId());
			}
		}
		
		initGroupsYml();
		initUsersYml();
		
		System.out.println("Broj grupa: " + identityService.createGroupQuery().count());
		System.out.println("Broj korisnika: " + identityService.createUserQuery().count());
		System.out.println("Broj korisnika u grupi narucilac:  " + identityService.createUserQuery().memberOfGroup("narucilac").count());
		System.out.println("Broj korisnika u grupi ponudjac:  " + identityService.createUserQuery().memberOfGroup("ponudjac").count());
		System.out.println("Broj korisnika u grupi komisija:  " + identityService.createUserQuery().memberOfGroup("komisija").count());
		System.out.println("Broj korisnika u grupi strucnoLiceZaKomisiju:  " + identityService.createUserQuery().memberOfGroup("strucnoLiceZaKomisiju").count());
		System.out.println("Broj korisnika u grupi stranoLice:  " + identityService.createUserQuery().memberOfGroup("stranoLice").count());
	}
}
