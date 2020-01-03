import com.kineticdata.bridgehub.adapter.BridgeAdapterTestBase;
import com.kineticdata.bridgehub.adapter.BridgeError;
import com.kineticdata.bridgehub.adapter.BridgeRequest;
import com.kineticdata.bridgehub.adapter.Count;
import com.kineticdata.bridgehub.adapter.Record;
import com.kineticdata.bridgehub.adapter.RecordList;
import com.kineticdata.bridgehub.adapter.sql.postgres.PostgresAdapter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PostgresTest extends BridgeAdapterTestBase{
        
    @Override
    public Class getAdapterClass() {
        return PostgresAdapter.class;
    }
    
    @Override
    public String getConfigFilePath() {
        return "src/test/resources/bridge-config.yml";
    }
    
    @Test
    @Override
    public void test_emptyRetrieve() throws Exception {
        BridgeError error = null;
        
        BridgeRequest request = new BridgeRequest();

        List<String> fields = Arrays.asList("id","Name","Description");
        
        request.setStructure("mytesttable");
        request.setFields(fields);
        request.setQuery("");
        
        RecordList recordList = null;
        Map<String,Object> recordMap = new HashMap<String,Object>();
        try {
            recordList = getAdapter().search(request);
        } catch (BridgeError e) {
            error = e;
            for (StackTraceElement el : e.getStackTrace()){ System.out.println(el.toString());; }
        }
        
//        assertNull(error);
//        assertTrue(recordList != null);
    }
    
    @Test
    public void test_count() throws Exception{
        BridgeError error = null;

        BridgeRequest request = new BridgeRequest();
        
        List<String> fields = Arrays.asList("id");
        request.setFields(fields);
        
        request.setStructure("Projects");
        request.setFields(fields);
        request.setQuery("projects");
        
        Count count = null;
        try {
            count = getAdapter().count(request);
        } catch (BridgeError e) {
            error = e;
        }
        
//        assertNull(error);
//        assertTrue(count.getValue() > 0);
    }
}

