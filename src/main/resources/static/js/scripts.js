    document.addEventListener("DOMContentLoaded", function() {
    const userInfo = document.querySelector(".user-profile");
    const dropdownMenu = document.querySelector(".dropdown-menu");

    userInfo.addEventListener("click", function() {
    dropdownMenu.classList.toggle("hidden");
});

    // Close the dropdown when clicking outside
    window.addEventListener("click", function(event) {
    if (!userInfo.contains(event.target)
    && !dropdownMenu.contains(event.target)) {
    dropdownMenu.classList.add("hidden");
}
});
});

    document.getElementById('menu-toggle').addEventListener(
    'click',
    function() {
    const sidebar = document.querySelector('.sidebar');
    const topBar = document.querySelector('.top-bar');
    const analyticsContainer = document
    .querySelector('.shared-container');

    // Toggle sidebar collapse
    sidebar.classList.toggle('collapsed');

    // Adjust top bar and analytics container
    topBar.classList.toggle('expanded');
    analyticsContainer.classList.toggle('expanded');
});

    document.addEventListener("DOMContentLoaded", function () {
        const popup = document.getElementById("multiStatePopup");
        const closeButton = document.getElementById("closePopupButton");
        const optionsState = document.getElementById("optionsState");
        const loadingState = document.getElementById("loadingState");
        const successState = document.getElementById("successState");
        const startGeneratingButton = document.getElementById("startGenerating");
        const downloadButton = document.getElementById("downloadReport");
        const openPopupButton = document.querySelector(".generate-report"); // Button to open popup
        const reportOptions = document.querySelectorAll(".report-option");

        let selectedReports = []; // Array to store selected report types

        // Show popup with the options state
        function showPopup() {
            popup.classList.remove("hidden");
            showSection(optionsState);
        }

        // Add listener to open popup
        openPopupButton.addEventListener("click", showPopup);

        // Close popup
        closeButton.addEventListener("click", function () {
            popup.classList.add("hidden");
        });

        // Show specific section
        function showSection(section) {
            // Hide all sections
            [optionsState, loadingState, successState].forEach((s) =>
                s.classList.add("hidden")
            );
            // Show the selected section
            section.classList.remove("hidden");
        }

        // Handle toggling of report options
        reportOptions.forEach((option) => {
            option.addEventListener("click", function () {
                const reportType = this.getAttribute("data-type");
                if (this.classList.contains("selected")) {
                    // Deselect option
                    this.classList.remove("selected");
                    selectedReports = selectedReports.filter((type) => type !== reportType);
                } else {
                    // Select option
                    this.classList.add("selected");
                    selectedReports.push(reportType);
                }
                console.log("Selected Reports:", selectedReports); // Debugging
            });
        });

        // Handle "Generate Now" button click
        startGeneratingButton.addEventListener("click", function () {
            if (selectedReports.length === 0) {
                alert("Please select at least one report type.");
                return;
            }
            showSection(loadingState); // Show loading state

            // Simulate report generation delay
            setTimeout(() => {
                showSection(successState); // Show success state
            }, 2000); // 2 seconds for demonstration
        });

        // Handle "Download" button click
        downloadButton.addEventListener("click", function () {
            const {jsPDF} = window.jspdf;

            const content = document.getElementById('analytics-container');

            html2canvas(content, {scale: 2}).then(canvas => {
                const pdf = new jsPDF('p', 'mm', 'a4'); // Create PDF instance (portrait, mm, A4 size)
                const imgData = canvas.toDataURL('image/png'); // Convert canvas to image

                const pdfWidth = pdf.internal.pageSize.getWidth();
                const pdfHeight = (canvas.height * pdfWidth) / canvas.width; // Maintain aspect ratio

                pdf.addImage(imgData, 'PNG', 0, 0, pdfWidth, pdfHeight); // Add image to PDF
                pdf.save('report.pdf'); // Save PDF
            });

            // Close the popup after downloading
            popup.classList.add("hidden");
            selectedReports = []; // Clear selected reports after download
        });
    });


    document.addEventListener('DOMContentLoaded', () => {
        // Get the current URL path
        const currentPath = window.location.pathname;

        // Select all sidebar menu links
        const menuLinks = document.querySelectorAll('.sidebar .menu a');

        // Loop through each link and check if it matches the current path
        menuLinks.forEach(link => {
            if (link.getAttribute('href') === currentPath) {
                link.classList.add('active'); // Add 'active' class
            } else {
                link.classList.remove('active'); // Remove 'active' class from others
            }
        });
    });


