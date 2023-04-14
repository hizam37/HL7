package com.hizam.rest_service.rest;
import com.hizam.rest_service.entity.Parameters;
import com.hizam.rest_service.registrator.PatientwithparametersRegistrator;
import com.hizam.rest_service.repository.PatientwithparametersRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;



@RequestScoped
@Path("/patient_parameters")
public class HL7REST {


    @Inject
    private PatientwithparametersRepository patientwithparametersRepository;

    @Inject
    private PatientwithparametersRegistrator patientwithparametersRegistrator;


    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_XML)
    public List<Parameters> getAllPatients(){
        return patientwithparametersRepository.getAll();
    }



    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Parameters getPatientById(@PathParam("id") long id) {
            return patientwithparametersRepository.getById(id);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Parameters registerpatient(Parameters item) {
     return patientwithparametersRegistrator.create(item);
    }



    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
        public Parameters deletePatientById(@PathParam("id") long id){

        return patientwithparametersRegistrator.delete(id);
    }
}
