package base64jsondecoder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Base64;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author samikhajlov
 */
public class Base64JsonDecoder {
    public static JsonObject decoder(String data) throws IOException{
        byte[] decodedRequest = Base64.getDecoder().decode(data);                            
        Reader reader = null;
        StringWriter writer = null;
        String charset = "UTF-8"; 
        ByteArrayInputStream bais = new ByteArrayInputStream(decodedRequest);
        InputStream ungzippedResponse = new GZIPInputStream( bais );
        reader = new InputStreamReader(ungzippedResponse, charset);
        writer = new StringWriter();
        char[] buffer = new char[10240];
        for (int length = 0; (length = reader.read(buffer)) > 0; ) {
            writer.write(buffer, 0, length);
        }
        String body = writer.toString();  
        JsonParser parser = new JsonParser();
        JsonObject pItem = (JsonObject) parser.parse(body);
        return pItem;
    }
}
