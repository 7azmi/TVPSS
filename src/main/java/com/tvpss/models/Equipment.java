package com.tvpss.models;

public class Equipment {
    private int userId; // Now the primary key
    private boolean logo;
    private boolean miniStudio;
    private boolean inSchoolRecording;
    private boolean uploadOnYoutube;
    private boolean recordingInsideOutside;
    private boolean externalAgencyCollaboration;
    private boolean greenScreenTechnology;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public boolean isUploadOnYoutube() { return uploadOnYoutube; }

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
