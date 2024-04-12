//package com.assign.SpringBootApp.util;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import org.springframework.boot.jackson.JsonComponent;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//@JsonComponent
//public class SqlDateDeserializer extends JsonDeserializer<Date> {
//
//    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
//
//    @Override
//    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
//        String dateStr = p.getText();
//        try {
//            java.util.Date utilDate = dateFormat.parse(dateStr);
//            return new Date(utilDate.getTime());
//        } catch (ParseException e) {
//            throw new IllegalArgumentException("Invalid date format: " + dateStr);
//        }
//    }
//}
