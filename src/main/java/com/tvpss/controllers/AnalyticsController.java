package com.tvpss.controllers;

import com.tvpss.models.Analytics;
import com.tvpss.repositories.AnalyticsRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.text.NumberFormat;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"/analytics", "/analytics/"})
public class AnalyticsController {

    private final AnalyticsRepository analyticsRepository;

    public AnalyticsController(AnalyticsRepository analyticsRepository) {
        this.analyticsRepository = analyticsRepository;
    }

    @GetMapping(value = {"/", ""})
    public String viewAnalytics(HttpSession session, Model model) {
        try {
            // Fetch analytics data from the repository
            List<Analytics> analyticsData = analyticsRepository.findAllAnalytics();

            // Format numbers for display
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            analyticsData.forEach(analytics -> {
                analytics.setFormattedActiveUsers(numberFormat.format(analytics.getActiveUsers()));
                analytics.setFormattedTotalUploads(numberFormat.format(analytics.getTotalUploads()));
                analytics.setFormattedTotalViews(numberFormat.format(analytics.getTotalViews()));
            });

            model.addAttribute("analyticsData", analyticsData);
            model.addAttribute("username", session.getAttribute("username"));
            model.addAttribute("role", session.getAttribute("role"));

            return "analytics_view"; // Returns the view name (e.g., analytics_view.html)
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Unable to load analytics data.");
            return "error";
        }
    }

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Map<String, List<Integer>>> getAnalyticsData() {
        List<Analytics> analyticsList = analyticsRepository.findAllAnalytics();

        // Group data by month
        Map<Month, List<Analytics>> groupedByMonth = analyticsList.stream()
                .collect(Collectors.groupingBy(analytics -> analytics.getDate().getMonth()));

        // Prepare response structure
        Map<String, Map<String, List<Integer>>> responseData = new HashMap<>();
        responseData.put("engagementRate", new HashMap<>());
        responseData.put("totalUploads", new HashMap<>());
        responseData.put("totalViews", new HashMap<>());
        responseData.put("activeUsers", new HashMap<>());

        // Populate metrics for each month
        for (Month month : groupedByMonth.keySet()) {
            List<Analytics> monthData = groupedByMonth.get(month);

            responseData.get("engagementRate").put(month.name(),
                    monthData.stream().map(Analytics::getEngagementRate).map(Float::intValue).collect(Collectors.toList()));

            responseData.get("totalUploads").put(month.name(),
                    monthData.stream().map(Analytics::getTotalUploads).collect(Collectors.toList()));

            responseData.get("totalViews").put(month.name(),
                    monthData.stream().map(Analytics::getTotalViews).collect(Collectors.toList()));

            responseData.get("activeUsers").put(month.name(),
                    monthData.stream().map(Analytics::getActiveUsers).collect(Collectors.toList()));
        }

        return responseData;
    }
}
