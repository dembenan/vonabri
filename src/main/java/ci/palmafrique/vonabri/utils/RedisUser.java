package ci.palmafrique.vonabri.utils;


import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import ci.palmafrique.vonabri.utils.dto.UserDto;
import lombok.extern.java.Log;
@Log
@Component
public class RedisUser  implements RedisValue<UserDto> {

	@Autowired
	private RedisTemplate<String, UserDto> redisUserTemplate;
	@Autowired
	private RedisTemplate<String, String> redisTokenTemplate;

	@Autowired
	private ParamConfig paramConfig;
	

 
	@Override
	public UserDto get(String key) {
		try {
			return redisUserTemplate.opsForValue().get(key);
		} catch (Exception e) {
			// TODO: handle exception
		}		return null;
	}
	
	public void saveValueWithoutExpiration(String key, UserDto userDto) {
		try {

			redisUserTemplate.opsForValue().set(key, userDto);
			UserDto verif = get(key);
			System.out.println("Redis  value saved============================>"+verif+"=====================================");
			
		} catch (Exception e) {
			e.printStackTrace();
//			log.warning("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}

	public long getExpiration(String key) {
		try {
			System.out.println("REDIS KEY"+key+"========EXPIRE AT=============================>"+redisUserTemplate.getExpire(key));

			return redisUserTemplate.getExpire(key);
			
		} catch (Exception e) {
			e.printStackTrace();
//			log.warning("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
		return 0;
		
	}

	@Override
	public boolean save(String key, UserDto user, boolean isDelay) {
		try {
			if(Utilities.isTrue(isDelay)) {
			 redisUserTemplate.opsForValue().set(key, user, 1, TimeUnit.DAYS);
			}else {
				redisUserTemplate.opsForValue().set(key, user);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	private Set<String> getKeys(String pattern) {
		try {
			return redisUserTemplate.keys(pattern + "*");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserDto> getClusterUser(String pattern) {
		try {
			return redisUserTemplate.opsForValue().multiGet(this.getKeys(pattern));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public UserDto  getValue(String key) {
		UserDto value = null;
		try {
			value = redisUserTemplate.opsForValue().get(key);
		} catch (Exception e) {
			//slf4jLogger.warn("getValue : " + e.getCause(), e.getMessage());
		}
		return value;
	}
	
	//TOKEN SMS ORANGE
	
	public void saveTokenWithExpirationInSecond(String key, String token,int expiration) {
		try {
			//set to 6Hours / minutes before
			redisTokenTemplate.opsForValue().set(key, token,expiration,TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			//slf4jLogger.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}

	public String  getValueToken(String key) {
		String value = null;
		try {
			value = redisTokenTemplate.opsForValue().get(key);
		} catch (Exception e) {
			//slf4jLogger.warn("getValue : " + e.getCause(), e.getMessage());
		}
		return value;
	}

	
	
	@Override
	public void delete(String key) {
		redisUserTemplate.delete(key);
	}

	@Override
	public boolean supports(String s) {
		// TODO Auto-generated method stub
		return false;
	}


	
	public void saveValueWithExpiration(String key, UserDto cmdsTosave) {
		try {
			//set to 6Hours / minutes before
			redisUserTemplate.opsForValue().set(key, cmdsTosave,paramConfig.getSessionInterval(),TimeUnit.HOURS);
		} catch (Exception e) {
			e.printStackTrace();
			//Logger.("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}
	
	public void saveValueWithExpirationMinutes(String key, UserDto cmdsTosave,int expiration) {
		try {
			//set to 6Hours / minutes before
			redisUserTemplate.opsForValue().set(key,cmdsTosave,expiration,TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
			//log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}
	public void setExpiration(String key,int expiration) {
		try {
			//set to 6Hours / minutes before
			redisUserTemplate.expire(key,expiration, TimeUnit.MINUTES);
			//redisUserTemplate.opsForValue().set(key,cmdsTosave,expiration,TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
			//log.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}
	public void saveValueWithExpirationInSecond(String key, UserDto cmdsTosave,int expiration) {
		try {
			//set to 6Hours / minutes before
			redisUserTemplate.opsForValue().set(key, cmdsTosave,expiration,TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			//slf4jLogger.warn("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}
	

	
	public void saveValueWithExpirationImmediatly(String key, UserDto cmdsTosave) {
		try {
			redisUserTemplate.opsForValue().set(key, cmdsTosave,1,TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			//slf4jLogger.info("saveValueWithExpiration : " + e.getCause(), e.getMessage());
		}
	}


}
