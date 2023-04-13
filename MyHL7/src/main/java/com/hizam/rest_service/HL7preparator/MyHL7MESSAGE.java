package com.hizam.rest_service.HL7preparator;



import ca.uhn.hl7v2.model.v24.datatype.CE;
import ca.uhn.hl7v2.model.v24.segment.OBX;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.DataTypeException;
import ca.uhn.hl7v2.model.v24.datatype.PL;
import ca.uhn.hl7v2.model.v24.datatype.XCN;
import ca.uhn.hl7v2.model.v24.datatype.XPN;
import ca.uhn.hl7v2.model.v24.message.ADT_A01;
import ca.uhn.hl7v2.model.v24.segment.EVN;
import ca.uhn.hl7v2.model.v24.segment.MSH;
import ca.uhn.hl7v2.model.v24.segment.PID;
import ca.uhn.hl7v2.model.v24.segment.PV1;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MyHL7MESSAGE {

    private ADT_A01 _adtMessage;


    public ADT_A01 Build() throws HL7Exception, IOException, ParserConfigurationException {
        String currentDateTimeString = getCurrentTimeStamp();
        _adtMessage = new ADT_A01();
        _adtMessage.initQuickstart("ADT", "A01", "P");
        createMshSegment(currentDateTimeString);
        createEvnSegment(currentDateTimeString);
        createPidSegment();
        createPv1Segment();
        createOBXSegment();
        return _adtMessage;
    }

    private void createPidSegment() {
        try {
            File fXmlFile = new File("C:\\Users\\ISLAM\\Downloads\\response.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("parameters");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    PID pid = _adtMessage.getPID();
                    XPN patientName = pid.getPatientName(0);
                    patientName.getFamilyName().getSurname()
                            .setValue(eElement.getElementsByTagName("family_name").item(0)
                                    .getTextContent());
                    patientName.getGivenName().setValue(eElement.getElementsByTagName(
                            "first_name").item(0).getTextContent());
                    pid.getPatientIdentifierList(0).getID().setValue("378785433211");
                }
            }
        } catch (HL7Exception e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private void createMshSegment(String currentDateTimeString) throws DataTypeException {
        MSH mshSegment = _adtMessage.getMSH();
        mshSegment.getFieldSeparator().setValue("|");
        mshSegment.getEncodingCharacters().setValue("^~\\&");
        mshSegment.getDateTimeOfMessage().getTimeOfAnEvent().setValue(currentDateTimeString);
        mshSegment.getMessageControlID().setValue(getSequenceNumber());
        mshSegment.getVersionID().getVersionID().setValue("2.4");
    }


    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    private String getSequenceNumber() {
        String facilityNumberPrefix = "1234";
        return facilityNumberPrefix.concat(getCurrentTimeStamp());
    }


    private void createEvnSegment(String currentDateTimeString) throws DataTypeException {
        EVN evn = _adtMessage.getEVN();
        evn.getEventTypeCode().setValue("A01");
        evn.getRecordedDateTime().getTimeOfAnEvent().setValue(currentDateTimeString);
    }


    private void createPv1Segment() throws DataTypeException {
        PV1 pv1 = _adtMessage.getPV1();
        pv1.getPatientClass().setValue("O");
        PL assignedPatientLocation = pv1.getAssignedPatientLocation();
        assignedPatientLocation.getFacility().getNamespaceID()
                .setValue("Some Treatment Facility Name");
        assignedPatientLocation.getPointOfCare().setValue("Some Point of Care");
        pv1.getAdmissionType().setValue("ALERT");
        XCN referringDoctor = pv1.getReferringDoctor(0);
        referringDoctor.getIDNumber().setValue("99999999");
        referringDoctor.getFamilyName().getSurname().setValue("Smith");
        referringDoctor.getGivenName().setValue("Jack");
        referringDoctor.getIdentifierTypeCode().setValue("456789");
        pv1.getAdmitDateTime().getTimeOfAnEvent().setValue(getCurrentTimeStamp());
    }

    private void createOBXSegment() throws DataTypeException, ParserConfigurationException {
        try {
            File fXmlFile = new File("C:\\Users\\ISLAM\\Downloads\\response.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("parameters");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    OBX obx = _adtMessage.getOBX();
                    CE ecg = obx.getUnits();
                    ecg.getCe1_Identifier().setValue(eElement.getElementsByTagName("NIBP").item(0).getTextContent());
                    obx.getObservationResultStatus().setValue(eElement.getElementsByTagName("ECG").item(0).getTextContent());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}