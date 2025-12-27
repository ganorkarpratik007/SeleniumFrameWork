package DataPackage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	
	
	public List<HashMap<String, String>> GetJsonDataToMap() throws IOException
	{
		
		//Read json to string
	    String jsoncontent = FileUtils.readFileToString(new File("D:\\AUTOMATION\\Zing\\SeleniumFrameWork\\src\\test\\java\\DataPackage\\PurchaseOrder.json"),StandardCharsets.UTF_8);
		//Read String to hashmap
	    ObjectMapper mapper = new ObjectMapper();
	    List<HashMap<String, String>> data = mapper.readValue(jsoncontent, new com.fasterxml.jackson.core.type.TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}
}
