package com.tvpss.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class Analytics {
    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int activeUsers;
    private int totalUploads;
    private int totalViews;
    private float engagementRate;
    private float activeUsersChange;
    private float totalUploadsChange;
    private float engagementRateChange;
    private float totalViewsChange;
    private String formattedActiveUsers;
    private String formattedTotalUploads;
    private String formattedTotalViews;

    // Constructor with all fields
    public Analytics(int id, LocalDate date, int activeUsers, float activeUsersChange, int totalUploads,
                     float totalUploadsChange, float engagementRate, float engagementRateChange,
                     int totalViews, float totalViewsChange) {
        this.id = id;
        this.date = date;
        this.activeUsers = activeUsers;
        this.activeUsersChange = activeUsersChange;
        this.totalUploads = totalUploads;
        this.totalUploadsChange = totalUploadsChange;
        this.engagementRate = engagementRate;
        this.engagementRateChange = engagementRateChange;
        this.totalViews = totalViews;
        this.totalViewsChange = totalViewsChange;
    }

    // Default constructor
    public Analytics() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }

    public float getActiveUsersChange() {
        return activeUsersChange;
    }

    public void setActiveUsersChange(float activeUsersChange) {
        this.activeUsersChange = activeUsersChange;
    }

    public int getTotalUploads() {
        return totalUploads;
    }

    public void setTotalUploads(int totalUploads) {
        this.totalUploads = totalUploads;
    }

    public float getTotalUploadsChange() {
        return totalUploadsChange;
    }

    public void setTotalUploadsChange(float totalUploadsChange) {
        this.totalUploadsChange = totalUploadsChange;
    }

    public float getEngagementRate() {
        return engagementRate;
    }

    public void setEngagementRate(float engagementRate) {
        this.engagementRate = engagementRate;
    }

    public float getEngagementRateChange() {
        return engagementRateChange;
    }

    public void setEngagementRateChange(float engagementRateChange) {
        this.engagementRateChange = engagementRateChange;
    }

    public int getTotalViews() {
        return totalViews;
    }

    public void setTotalViews(int totalViews) {
        this.totalViews = totalViews;
    }

    public float getTotalViewsChange() {
        return totalViewsChange;
    }

    public void setTotalViewsChange(float totalViewsChange) {
        this.totalViewsChange = totalViewsChange;
    }

    public String getFormattedActiveUsers() {
        return formattedActiveUsers;
    }

    public void setFormattedActiveUsers(String formattedActiveUsers) {
        this.formattedActiveUsers = formattedActiveUsers;
    }

    public String getFormattedTotalUploads() {
        return formattedTotalUploads;
    }

    public void setFormattedTotalUploads(String formattedTotalUploads) {
        this.formattedTotalUploads = formattedTotalUploads;
    }

    public String getFormattedTotalViews() {
        return formattedTotalViews;
    }

    public void setFormattedTotalViews(String formattedTotalViews) {
        this.formattedTotalViews = formattedTotalViews;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Analytics{" +
                "id=" + id +
                ", date=" + date +
                ", activeUsers=" + activeUsers +
                ", activeUsersChange=" + activeUsersChange +
                ", totalUploads=" + totalUploads +
                ", totalUploadsChange=" + totalUploadsChange +
                ", engagementRate=" + engagementRate +
                ", engagementRateChange=" + engagementRateChange +
                ", totalViews=" + totalViews +
                ", totalViewsChange=" + totalViewsChange +
                '}';
    }
}
