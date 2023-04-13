package com.hizam.rest_service.rest;
import com.hizam.rest_service.entity.Parameters;
import com.hizam.rest_service.registrator.PatientwithparametersRegistrator;
import com.hizam.rest_service.repository.PatientwithparametersRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;



@RequestScoped
@Path("/patient_parameters")
public class HL7REST {

    @Inject
    private Logger logger;

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
    public Parameters getPatientById(@PathParam("id") long id) throws IOException {
            URL url = new URL("http://localhost:8080/hl7/api/patient_parameters/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        int responseCode = connection.getResponseCode();
        if(responseCode != 200) {
            System.out.println("Error: Response code " + responseCode);
        }
            Scanner scanner = new Scanner(url.openStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNext()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            String responseData = response.toString();
            FileWriter fileWriter = new FileWriter(
                    "PATH\\.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(responseData);
            printWriter.close();
            logger.info("Data saved to file.");
            return patientwithparametersRepository.getById(id);
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Parameters registerpatient(Parameters item) {
     return patientwithparametersRegistrator.create(item);
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Parameters updateparameters(Parameters item) {
        return patientwithparametersRegistrator.update(item);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
        public Parameters deletePatientById(@PathParam("id") long id){

        return patientwithparametersRegistrator.delete(id);
    }
}
