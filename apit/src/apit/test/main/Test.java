package apit.test.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public  class Test{
	
	public static final String PARAMETER_ID = "_id";
	public static final String PARAMETER_TOKEN = "_token";
	
	protected static Map< String, Object > paramMap = new HashMap< String, Object >( ); // 全局参数
	
	public static final String BASE = "http://localhost:8080/v1/";
	
	static{
		paramMap.put( PARAMETER_ID, 5 );
		paramMap.put( PARAMETER_TOKEN, "6ade885cc0ef519390a4ea7f3268e6bd" );
	}
	
	public static void main( String [ ] args ) {
		  // 创建HttpClient实例     
        HttpClient httpclient = new DefaultHttpClient();  
        // 创建Get方法实例     
        HttpGet httpgets = new HttpGet(BASE+"rsa.jhtml");    
        HttpResponse response=null;
		try{
			response = httpclient.execute(httpgets);
			HttpEntity entity = response.getEntity();  
			if (entity != null) {    
	            InputStream instreams = entity.getContent();    
	            String str = convertStreamToString(instreams);  
	            System.out.println(str);  
	            // Do not need the rest    
	            httpgets.abort();    
	        }  
		}catch( ClientProtocolException e ){
			e.printStackTrace();
		}catch( IOException e ){
			e.printStackTrace();
		}    
    }  
      
    public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
	
    
    
}