package com.hizam.rest_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


     @Column(name = "first_name")
     private String first_name;

     @Column(name = "family_name")
     private String family_name;

    @Column(name = "ssn_number")
    private int ssn_number;

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
        return id == that.id && ssn_number == that.ssn_number && ECG == that.ECG
                && Puls == that.Puls
                && Breathing == that.Breathing && NIBP == that.NIBP && Objects.equals(
                first_name, that.first_name) && Objects.equals(family_name, that.family_name)
                && Objects.equals(date_of_birth, that.date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, family_name, ssn_number, date_of_birth, ECG, Puls,
                Breathing, NIBP);
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", ssn_number=" + ssn_number +
                ", date_of_birth=" + date_of_birth +
                ", ECG=" + ECG +
                ", Puls=" + Puls +
                ", Breathing=" + Breathing +
                ", NIBP=" + NIBP +
                '}';
    }
}
