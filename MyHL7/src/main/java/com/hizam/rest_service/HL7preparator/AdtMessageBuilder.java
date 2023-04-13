package com.hizam.rest_service.HL7preparator;


import java.io.IOException;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.Message;
import javax.xml.parsers.ParserConfigurationException;

public class AdtMessageBuilder {

    public static Message createMessage(String messageType)
            throws HL7Exception, IOException, ParserConfigurationException {


        if ( messageType.equals("A01") )
        {
            return new MyHL7MESSAGE().Build();
        }

        throw new RuntimeException(String.format("%s message type is not supported yet. Extend this if you need to", messageType));

    }
}
