package activiti.spring.loanRequest.deploy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;

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
		/*
		 * Provo proveravamo da li imamo vec neke korisnike i grupe, pa ako nemamo, onda dodajemo
		 */
		long groupsNum =  identityService.createGroupQuery().count();
		if (groupsNum == 0)
			initGroupsYml();
		long usersNum = identityService.createUserQuery().count();
		if (usersNum == 0)
			initUsersYml();
		System.out.println("Broj grupa: " + identityService.createGroupQuery().count());
		System.out.println("Broj korisnika: " + identityService.createUserQuery().count());
		System.out.println("Broj korisnika u grupi bankar:  " + identityService.createUserQuery().memberOfGroup("bankar").count());
	}


}
