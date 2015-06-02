/*
 * 02.06.2015
 * J.Arrasz
 */
package net.contargo.architecture_strucurizr;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.io.StructurizrWriterException;
import com.structurizr.io.json.JsonWriter;
import java.io.StringWriter;
import static net.contargo.architecture_strucurizr.Architecture.log;
import org.apache.logging.log4j.message.SimpleMessage;

/**
 * Runs the entire process architecutre diagramm process.
 *
 * @author Joachim Arrasz, synyx GmbH & Co. KG
 */
public class StructurizrRunner {

    //technical informations from structurizr
    private static final String STRUCTURIZR_URL = "https://api.structurizr.com";
    private static final String SECRET = "secret";
    private static final String KEY = "key";
    private static final Long WORKSPACE_ID = 1234L;

    public static void main(String args[]) throws Exception {

        StructurizrClient structurizrClient = new StructurizrClient(STRUCTURIZR_URL, KEY, SECRET);
        Workspace space = structurizrClient.getWorkspace(WORKSPACE_ID);

        Architecture contargoArchitecture = new Architecture();
        contargoArchitecture.init(space);    

        try {
            if(args.length > 0 && args[0].equals("transmit"))
                transmitWorkspaceData(space);
            else
                renderModelasJSON(space);
        } catch (Exception ex) {
            log.error(new SimpleMessage("Could not transmit Workspace to Stucturizr!"), ex);
        }

    }

    private static void transmitWorkspaceData(Workspace contargoCore) throws Exception {
        StructurizrClient structurizrClient = new StructurizrClient(STRUCTURIZR_URL, KEY, SECRET);
        contargoCore.setId(WORKSPACE_ID);
        structurizrClient.putWorkspace(contargoCore);
    }

    private static void renderModelasJSON(Workspace contargoCore) throws StructurizrWriterException {
        // output the model as JSON
        JsonWriter jsonWriter = new JsonWriter(true);
        StringWriter stringWriter = new StringWriter();
        jsonWriter.write(contargoCore, stringWriter);
        log.trace(stringWriter.toString());

    }

}
