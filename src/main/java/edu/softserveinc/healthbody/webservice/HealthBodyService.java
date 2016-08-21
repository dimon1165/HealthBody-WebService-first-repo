package edu.softserveinc.healthbody.webservice;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.softserveinc.healthbody.dto.CompetitionDTO;
import edu.softserveinc.healthbody.dto.GroupDTO;
import edu.softserveinc.healthbody.dto.UserCompetitionsDTO;
import edu.softserveinc.healthbody.dto.UserDTO;

@WebService
public interface HealthBodyService {

	@WebMethod
	void createUser(UserDTO userDTO);

	@WebMethod
	UserDTO getUserByLogin(String login);

	@WebMethod
	void updateUser(UserDTO userDTO);

	@WebMethod
	void lockUser(String login, boolean isDisabled);

	@WebMethod
	List<UserDTO> getAllUsers(int partNumber, int partSize);

	@WebMethod
	List<UserDTO> getAllUserstoAddInCompetition(int partNumber, int partSize);

	@WebMethod
	List<UserDTO> getAllUsersinCompetition(int partNumber, int partSize);

	@WebMethod
	List<GroupDTO> getAllGroups(int partNumber, int partSize);

	@WebMethod
	GroupDTO getGroupByName(String name);

	@WebMethod
	GroupDTO getGroupById(String id);

	@WebMethod
	String getDescriptionOfGroup(String name);

	@WebMethod
	CompetitionDTO getCompetitionViewByName(String name);

	@WebMethod
	void updateGroup(GroupDTO groupDTO);

	@WebMethod
	List<CompetitionDTO> getAllCompetitions(int partNumber, int partSize);

	@WebMethod
	List<CompetitionDTO> getAllActiveCompetitions(int partNumber, int partSize);

	@WebMethod
	List<CompetitionDTO> getAllCompetitionsByUser(int partNumber, int partSize, String login);

	@WebMethod
	List<CompetitionDTO> getAllActiveCompetitionsByUser(int partNumber, int partSize, String login);

	@WebMethod
	void createCompetition(CompetitionDTO competitionDTO);
	
	@WebMethod
	void updateCompetition(CompetitionDTO competitionDTO);
	
	@WebMethod
	boolean addUserInCompetition(String nameCompetition, String nameUser);

	@WebMethod
	boolean addUserInCompetitionView(String nameCompetition, String nameUser);

	@WebMethod
	boolean removeUserFromCompetition(String nameCompetition, String nameUser);

	@WebMethod
	List<GroupDTO> getAllGroupsParticipants(int partNumber, int partSize);

	@WebMethod
	UserCompetitionsDTO getUserCompetition(String nameCompetition, String nameUser);

	@WebMethod
	void deleteUserCompetition(String nameCompetition, String nameUser);

}
