package com.hizam.rest_service.HL7preparator;

import ca.uhn.hl7v2.DefaultHapiContext;
import ca.uhn.hl7v2.HapiContext;
import ca.uhn.hl7v2.app.Connection;
import ca.uhn.hl7v2.app.Initiator;
import ca.uhn.hl7v2.model.Message;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.parser.Parser;

public class Hl7MessageSender {

    private static final int PORT_NUMBER = 8001;

    private static HapiContext context = new DefaultHapiContext();

    public static void main(String[] args) {

        try {

            ADT_A01 adtMessage = (ADT_A01) AdtMessageBuilder.createMessage("A01");


            Connection connection = context.newClient("localhost", PORT_NUMBER, false);


            Initiator initiator = connection.getInitiator();

            Parser parser = context.getPipeParser();
            System.out.println("Sending message:" + "\n" + parser.encode(adtMessage));
            Message response = initiator.sendAndReceive(adtMessage);
            String responseString = parser.encode(response);
            System.out.println("Received response:\n" + responseString);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}