/*
 * 01.06.2015
 *
 * J.Arrasz
 */
package net.contargo.architecture_strucurizr;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.Location;

import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.ComponentView;
import com.structurizr.view.ViewSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Main class.
 *
 * @author Joachim Arrasz, synyx GmbH & Co. KG
 */
public class Architecture {

    public static Logger log = LogManager.getLogger(Architecture.class.getName());

    public static final String DESC = "Beschreibung des Contargo Core Projekts";
    public static final String CONTARGO_CORE = "Core System";

    private Workspace contargoCore;

    public Architecture() {
    }

    public void init(Workspace contargoCore) {
        log.entry();

        //intitialisation
        Model model = initArchitectureWorkspace(contargoCore);
        ViewSet views = contargoCore.getViews();

        doModelling(model);
    }

    private Model initArchitectureWorkspace(Workspace contargoCore) {
        contargoCore = new Workspace(CONTARGO_CORE, DESC);
        return contargoCore.getModel();

    }

    private void doModelling(Model model) {
        SoftwareSystem sys = model.addSoftwareSystem(Location.Internal, CONTARGO_CORE, DESC);

        Person contargoUser = model.addPerson("Contargo System User", "NONE");
        contargoUser.uses(sys, "Eine Beschreibung");

        Container container = sys.addContainer("My Name", "My Description", "My technology");
        contargoUser.uses(container, "Uses as XYZ");

    }

}
