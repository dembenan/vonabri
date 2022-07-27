package ci.palmafrique.vonabri.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ci.palmafrique.vonabri.dao.repository.UserRepository;
import ci.palmafrique.vonabri.utils.Utilities;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    List<Object> emptylist = new ArrayList<Object>();


    Collection<Object> empty;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		ci.palmafrique.vonabri.dao.entity.User user = userRepository.findByEmail(email,false);
		
//		ci.palmafrique.vonabri.dao.entity.User s = Utilities.getSupers(email);
		
	    	if (user != null && user.getEmail().equals(email)) {
				return new User(user.getEmail(), user.getPassword(),new ArrayList<>());
			} else {
				System.out.println( "loadUserByUsername  dans ELSE "   );
				return null ;
//				throw new UsernameNotFoundException("User not found with email: " + email);
			}
		

	}
	
	public UserDetails loadUserCommercial(String email) throws UsernameNotFoundException {
   	    ci.palmafrique.vonabri.dao.entity.User user = userRepository.findByEmail(email,false);
    	
    	if (user != null && user.getEmail().equals(email)) {
			return new User(user.getEmail(), user.getPassword(),
					new ArrayList<>());
		} else {
			return null ;
//			throw new UsernameNotFoundException("User not found with email: " + email);
		}
	}

	public UserDetails loadUserByUsernameEmailAndPassword(String email,String passcode) throws UsernameNotFoundException {
		ci.palmafrique.vonabri.dao.entity.User user = userRepository.findByEmail(email,false);
    	
    	if (user != null ) {
			return new User(email, passcode,new ArrayList<>());
		} else {
			return null ;
//			throw new UsernameNotFoundException("User Existing  with email: " + email);
		}
	}
 

//    @Override
//    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
//
//    	User user = userRepository.findByPhoneNumber(phone,false);  
//    	
//    	if (user != null && user.getPhoneNumber().equals(phone)) {
// 			return phone;
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + phone);
//		}
//
//    	//return (UserDetails) new User(user.getLogin(), user.getPassword(), empty); // UserPrinciple implements UserDetails
//    }
}
