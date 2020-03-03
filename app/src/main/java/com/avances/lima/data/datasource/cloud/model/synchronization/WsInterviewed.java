package com.avances.lima.data.datasource.cloud.model.synchronization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsInterviewed {

    @SerializedName("TituloEntrevistado")
    @Expose
    private String interviewedTittle;

    @SerializedName("Entrevistado")
    @Expose
    private String interviewed;

    @SerializedName("ImagenEntrevistado")
    @Expose
    private String interviewedImage;

    @SerializedName("AudioEntrevistado")
    @Expose
    private String interviewedAudio;

    public WsInterviewed(String interviewedTittle,String interviewed,String interviewedImage,String interviewedAudio)
    {
        this.interviewedTittle=interviewedTittle;
        this.interviewed=interviewed;
        this.interviewedAudio = interviewedAudio;
        this.interviewedImage=interviewedImage;
    }

    public String getInterviewedTittle() {
        return interviewedTittle;
    }

    public void setInterviewedTittle(String interviewedTittle) {
        this.interviewedTittle = interviewedTittle;
    }

    public String getInterviewed() {
        return interviewed;
    }

    public void setInterviewed(String interviewed) {
        this.interviewed = interviewed;
    }

    public String getInterviewedAudio() {
        return interviewedAudio;
    }

    public void setInterviewedAudio(String interviewedAudio) {
        this.interviewedAudio = interviewedAudio;
    }

    public String getInterviewedImage() {
        return interviewedImage;
    }

    public void setInterviewedImage(String interviewedImage) {
        this.interviewedImage = interviewedImage;
    }
}
