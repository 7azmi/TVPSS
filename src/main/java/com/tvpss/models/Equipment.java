package com.tvpss.models;

import javax.persistence.*;

@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false)
    private int userId;

    private String name;
    private boolean logo;
    private boolean miniStudio;
    private boolean inSchoolRecording;
    private boolean uploadOnYoutube;
    private boolean recordingInsideOutside;
    private boolean externalAgencyCollaboration;
    private boolean greenScreenTechnology;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLogo() {
        return logo;
    }

    public void setLogo(boolean logo) {
        this.logo = logo;
    }

    public boolean isMiniStudio() {
        return miniStudio;
    }

    public void setMiniStudio(boolean miniStudio) {
        this.miniStudio = miniStudio;
    }

    public boolean isInSchoolRecording() {
        return inSchoolRecording;
    }

    public void setInSchoolRecording(boolean inSchoolRecording) {
        this.inSchoolRecording = inSchoolRecording;
    }

    public boolean isUploadOnYoutube() {
        return uploadOnYoutube;
    }

    public void setUploadOnYoutube(boolean uploadOnYoutube) {
        this.uploadOnYoutube = uploadOnYoutube;
    }

    public boolean isRecordingInsideOutside() {
        return recordingInsideOutside;
    }

    public void setRecordingInsideOutside(boolean recordingInsideOutside) {
        this.recordingInsideOutside = recordingInsideOutside;
    }

    public boolean isExternalAgencyCollaboration() {
        return externalAgencyCollaboration;
    }

    public void setExternalAgencyCollaboration(boolean externalAgencyCollaboration) {
        this.externalAgencyCollaboration = externalAgencyCollaboration;
    }

    public boolean isGreenScreenTechnology() {
        return greenScreenTechnology;
    }

    public void setGreenScreenTechnology(boolean greenScreenTechnology) {
        this.greenScreenTechnology = greenScreenTechnology;
    }
}
