package com.tvpss.repositories;

import com.tvpss.models.Analytics;
import java.util.List;

public interface AnalyticsRepository {
    List<Analytics> findAllAnalytics();
}
