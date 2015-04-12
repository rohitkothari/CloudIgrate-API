package cloudigrate.api.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import cloudigrate.api.facade.ComputeFacade;

/**
 * @author sumantmurke
 * 
 */
/*
 * Controller for delegating all requests pertaining to COMPUTE component of CloudIgrate.net
 * 
 * Normal Flow for request processing:
 * 		Controller -> Facade -> Implementation -> AWS or Google
 */

@Path("/Compute")
public class ComputeController {
	
//Creating object of Controller Facade of compute	
ComputeFacade computefacade = new ComputeFacade();	

/*
 * Create a Virtual Machine
 */

@Path("/create/{vmname}/{vmdescription}")
@POST
public String createVM(@PathParam("vmname") String vmname,@PathParam("vmdescription") String vmdescription){
	System.out.println(" name of vm is "+vmname+" and description is "+vmdescription);
	

	return computefacade.createVM(vmname, vmdescription);
	

}

@Path("/stop/{instanceId}")
@POST
public String stopVM(@PathParam("instanceId") String instanceId){
	
	computefacade.stopVM(instanceId);
	return "VM : "+instanceId+" Stopped";
}
	
@Path("/terminate/{instanceId}")
@POST
public String terminateVM(@PathParam("instanceId") String instanceId){
	
	computefacade.terminateInstance(instanceId);
	
	return "terminated instance";
}

}
