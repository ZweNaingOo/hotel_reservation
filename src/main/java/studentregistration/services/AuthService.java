package studentregistration.services;

import studentregistration.models.UserResponseDTO;
import studentregistration.persistant.UserRepository;

public class AuthService {
	
	private final UserRepository user_repo = new UserRepository();
	public boolean check(String email) {
		if(user_repo.findByEmail(email)!=null)return true;
		else return false;
	}
	public boolean check(String email,String password) {
		UserResponseDTO user=user_repo.findByEmail(email);
		if(user==null)return false;
		else if(!user.getPassword().equals(password))return false;
		else return true;
	}
	public UserResponseDTO checkAndGetUser(String email,String password) {
		UserResponseDTO user=user_repo.findByEmail(email);
		if(user==null)return null;
		else {
			if(!user.getPassword().equals(password))return null;
			else return user;
		}
	}
}
