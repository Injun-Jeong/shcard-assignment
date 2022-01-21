package ctr;

import java.io.IOException;
import java.util.List;

import api.JedisApi;
import api.NaverApi;
import model.CtrRequest;
import model.CtrResponse;
import model.InfoData;

public class AppCtr {
	public static CtrResponse sel(CtrRequest reqCtr) throws IOException {
		CtrResponse result = new CtrResponse();
		
		// redis 조회
		result.setInfoData(JedisApi.getEnNmFrom(reqCtr.getInfoData()));
		
		// naver 조회
		result.setNaverData(NaverApi.getEnNmFrom(reqCtr.getInfoData()));
		
		return result;
	}
	
	
	public static void sav(CtrRequest reqCtr) {
		JedisApi.setEnNmBy(reqCtr.getInfoData());
	}
}
