package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import model.InfoData;
import model.InfoData.InfoDataBuilder;

public class NaverApi {
	private static final String URL_BASE = "https://dict.naver.com/name-to-roman/translation/?query=";
    private static final String GET = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";
    
    
	public static List<InfoData> getEnNmFrom(InfoData infoData) throws IOException {
		return getEnNmPopular(callNaverInfo(infoData), infoData);
	}
	
	
	private static String callNaverInfo(InfoData infoData) throws IOException {
		String URL = URL_BASE.concat(infoData.getKrLastNm())
							 .concat("%20")
							 .concat(infoData.getKrFirstNm())
							 .concat("&where=name");
		
		URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(GET);
        connection.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null)  {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        return stringBuffer.toString();
	}
	
	
	private static List<InfoData> getEnNmPopular(String naverInfo,InfoData infoData) {
		List<InfoData> result = new ArrayList<>();
		String[] arrStr = naverInfo.split("engname_popular");
        
        if (arrStr.length > 1) {
        	arrStr[1] = arrStr[1].replaceAll("<td class=\"cell_engname\" title=\"", "xyzxyz");
        	arrStr[1] = arrStr[1].replaceAll("\"><a href=\"http://search.naver.com/search.naver?", "xyzxyz");
        	
        	String[] arrTrgt = arrStr[1].split("xyzxyz");
        	for (int idx = 0; idx < arrTrgt.length; idx++) {
        		if ( idx % 2 == 1 ) {
        			String[] enNm = arrTrgt[idx].split(" ");
        			InfoData item = new InfoDataBuilder()
										.enLastNm(enNm[0])
										.enFirstNm(enNm[1])
										.krLastNm(infoData.getKrLastNm())
										.krFirstNm(infoData.getKrFirstNm())
										.build();
        			result.add(item);
        		}
        	}
        }
		
		return result;
	}
}
