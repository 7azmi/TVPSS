// Initialize all charts
async function initializeCharts() {
    // Fetch data from the analytics endpoint
    const response = await fetch("/analytics/data");
    const analyticsData = await response.json();

    // Parse the response to get the required data for each chart
    const monthlyData = {
        engagementRate: analyticsData.engagementRate, // e.g., { "JANUARY": [15, 20, ...], ... }
        totalUploads: analyticsData.totalUploads,   // e.g., { "JANUARY": [5, 10, ...], ... }
        totalViews: analyticsData.totalViews,       // e.g., { "JANUARY": [100, 200, ...], ... }
        activeUsers: analyticsData.activeUsers,     // e.g., { "JANUARY": [500, 600, ...], ... }
    };

    // Default month for initial display
    const defaultMonth = "JANUARY";

    // Engagement Rate Chart
    const engagementRateCtx = document
        .getElementById("engagementRateChart")
        .getContext("2d");
    const engagementRateChart = new Chart(engagementRateCtx, {
        type: "line",
        data: {
            labels: Array.from({ length: monthlyData.engagementRate[defaultMonth].length }, (_, i) => `Day ${i + 1}`),
            datasets: [
                {
                    label: "Engagement Rate",
                    data: monthlyData.engagementRate[defaultMonth],
                    borderColor: "#2b3fec",
                    backgroundColor: "rgba(43, 63, 236, 0.2)",
                    fill: true,
                },
            ],
        },
    });

    // Total Uploads Chart
    const totalUploadsCtx = document
        .getElementById("totalUploadsChart")
        .getContext("2d");
    const totalUploadsChart = new Chart(totalUploadsCtx, {
        type: "bar",
        data: {
            labels: Array.from({ length: monthlyData.totalUploads[defaultMonth].length }, (_, i) => `Day ${i + 1}`),
            datasets: [
                {
                    label: "Total Uploads",
                    data: monthlyData.totalUploads[defaultMonth],
                    backgroundColor: "rgba(255, 159, 64, 0.5)",
                    borderColor: "rgba(255, 159, 64, 1)",
                },
            ],
        },
    });

    // Total Views Chart
    const totalViewsCtx = document
        .getElementById("totalViewsChart")
        .getContext("2d");
    const totalViewsChart = new Chart(totalViewsCtx, {
        type: "line",
        data: {
            labels: Array.from({ length: monthlyData.activeUsers[defaultMonth].length }, (_, i) => `Day ${i + 1}`),
            datasets: [
                {
                    label: "Total Views",
                    data: monthlyData.totalViews[defaultMonth],
                    backgroundColor: "rgba(255,144,102,0.2)",
                    borderColor: "#FF9066",
                    fill: true,
                },
            ],
        },
    });

    // Active Users Chart
    const activeUsersCtx = document
        .getElementById("activeUsersChart")
        .getContext("2d");
    const activeUsersChart = new Chart(activeUsersCtx, {
        type: "line",
        data: {
            labels: Array.from({ length: monthlyData.activeUsers[defaultMonth].length }, (_, i) => `Day ${i + 1}`),
            datasets: [
                {
                    label: "Active Users",
                    data: monthlyData.activeUsers[defaultMonth],
                    borderColor: "#4caf50",
                    backgroundColor: "rgba(76, 175, 80, 0.2)",
                    fill: true,
                },
            ],
        },
    });

    // Add filter functionality for each graph
    document
        .getElementById("engagementRateFilter")
        .addEventListener("change", function () {
            const selectedMonth = this.value.toUpperCase();
            engagementRateChart.data.datasets[0].data =
                monthlyData.engagementRate[selectedMonth];
            engagementRateChart.update();
        });

    document
        .getElementById("totalUploadsFilter")
        .addEventListener("change", function () {
            const selectedMonth = this.value.toUpperCase();
            totalUploadsChart.data.datasets[0].data =
                monthlyData.totalUploads[selectedMonth];
            totalUploadsChart.update();
        });

    document
        .getElementById("totalViewsFilter")
        .addEventListener("change", function () {
            const selectedMonth = this.value.toUpperCase();
            totalViewsChart.data.datasets[0].data =
                monthlyData.totalViews[selectedMonth];
            totalViewsChart.update();
        });

    document
        .getElementById("activeUsersFilter")
        .addEventListener("change", function () {
            const selectedMonth = this.value.toUpperCase();
            activeUsersChart.data.datasets[0].data =
                monthlyData.activeUsers[selectedMonth];
            activeUsersChart.update();
        });
}

// Call initializeCharts after DOM is fully loaded
document.addEventListener("DOMContentLoaded", initializeCharts);
