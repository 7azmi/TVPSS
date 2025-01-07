package com.tvpss.models;

public class Equipment {
    private Long id;
    private int userId; // Foreign key to the user who owns the equipment
    private String name;
    private boolean logo;
    private boolean miniStudio;
    private boolean inSchoolRecording;
    private boolean uploadOnYouTube;
    private boolean recordingInsideOutside;
    private boolean externalAgencyCollaboration;
    private boolean greenScreenTechnology;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isUploadOnYouTube() {
        return uploadOnYouTube;
    }

    public void setUploadOnYouTube(boolean uploadOnYouTube) {
        this.uploadOnYouTube = uploadOnYouTube;
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
