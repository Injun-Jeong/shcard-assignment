package api;

import java.util.Objects;

import model.InfoData;
import model.InfoData.InfoDataBuilder;
import redis.clients.jedis.Jedis;

public class JedisApi {
	public static InfoData getEnNmFrom(InfoData infoData) {
		InfoData result = new InfoDataBuilder()
								.enLastNm("")
								.enFirstNm("")
								.krLastNm(infoData.getKrLastNm())
								.krFirstNm(infoData.getKrFirstNm())
								.build();
		
		// 조회 대상
		String krNm = infoData.getKrLastNm().concat(" ").concat(infoData.getKrFirstNm());
		
		// 조회 수행
		Jedis jedis = new Jedis("175.116.84.203", 6379);
		jedis.auth("ss12!(");
		String enNm = jedis.get(krNm);
		
		// 결과값 세팅
		if ( !Objects.isNull(enNm) ) {
			String[] s = enNm.split(" ");
	    	result.setEnLastNm(s[0]);
	    	result.setEnFirstNm(s[1]);
		}
		  
	    return result;
	}
	
	
	public static void setEnNmBy(InfoData infoData) {
		// 저장 대상
		String krNm = infoData.getKrLastNm().concat(" ").concat(infoData.getKrFirstNm());
		String enNm = infoData.getEnLastNm().concat(" ").concat(infoData.getEnFirstNm());
		
		// 저장 수행
		Jedis jedis = new Jedis("175.116.84.203", 6379);
		jedis.auth("ss12!(");
		jedis.set(krNm, enNm);
	}
}
