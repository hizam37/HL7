package com.hizam.rest_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Timer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

@XmlRootElement
 @Entity
 @Getter
 @Setter
 @NoArgsConstructor
 @AllArgsConstructor
 @Table(name = "parameters")
 public class Parameters implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private long id;

     @Column(name = "national_number")
     private int national_number;

     @Column(name = "first_name")
     private String first_name;

     @Column(name = "family_name")
     private String family_name;

    @JsonFormat(pattern="yyyy-MM-dd")
     @Column(name = "date_of_birth")
     private Date date_of_birth;

     @Column(name = "ECG")
     private int ECG;

     @Column(name = "Puls")
     private int Puls;

     @Column(name = "Breathing")
     private int Breathing;

     @Column(name = "NIBP")
     private int NIBP;

     @Override
     public boolean equals(Object o) {
         if (this == o) {
             return true;
         }
         if (o == null || getClass() != o.getClass()) {
             return false;
         }
         Parameters that = (Parameters) o;
         return id == that.id && national_number == that.national_number && ECG == that.ECG
                 && Puls == that.Puls && Breathing == that.Breathing && NIBP == that.NIBP
                 && Objects.equals(first_name, that.first_name) && Objects.equals(
                 family_name, that.family_name) && Objects.equals(date_of_birth,
                 that.date_of_birth);
     }

     @Override
     public int hashCode() {
         return Objects.hash(id, national_number, first_name, family_name, date_of_birth, ECG, Puls,
                 Breathing, NIBP);
     }

     @Override
     public String toString() {
         return "Parameters{" +
                 "id=" + id +
                 ", national_number=" + national_number +
                 ", first_name='" + first_name + '\'' +
                 ", family_name='" + family_name + '\'' +
                 ", date_of_birth=" + date_of_birth +
                 ", ECG=" + ECG +
                 ", Puls=" + Puls +
                 ", Breathing=" + Breathing +
                 ", NIBP=" + NIBP +
                 '}';
     }
 }
