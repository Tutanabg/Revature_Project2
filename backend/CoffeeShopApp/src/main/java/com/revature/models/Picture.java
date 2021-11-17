package com.revature.models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pic_id")
    private int picID;

    @Column(name = "pic_name")
    private String picName;

    @Column(name = "pic_type")
    private Byte[] picType;

    public Picture() {
    }

    public Picture(int picID, String picName, Byte[] picType) {
        this.picID = picID;
        this.picName = picName;
        this.picType = picType;
    }

    public int getPicID() {
        return picID;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public Byte[] getPicType() {
        return picType;
    }

    public void setPicType(Byte[] picType) {
        this.picType = picType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture)) return false;
        Picture picture = (Picture) o;
        return getPicID() == picture.getPicID() && Objects.equals(getPicName(), picture.getPicName()) && Arrays.equals(getPicType(), picture.getPicType());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getPicID(), getPicName());
        result = 31 * result + Arrays.hashCode(getPicType());
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "picID=" + picID +
                ", picName='" + picName + '\'' +
                ", picType=" + Arrays.toString(picType) +
                '}';
    }
}
