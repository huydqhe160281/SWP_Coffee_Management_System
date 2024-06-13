<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- Head Import Start -->
<title></title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta name="keywords" content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
<meta name="author" content="Codedthemes" />
<!-- Favicon icon -->
<link rel="icon" href="assets/images/favicon.ico" type="image/x-icon" />
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,700" rel="stylesheet" />
<!-- waves.css -->
<link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
<!-- Required Fremwork -->
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap/css/bootstrap.min.css" />
<!-- waves.css -->
<link rel="stylesheet" href="assets/pages/waves/css/waves.min.css" type="text/css" media="all" />
<!-- themify-icons line icon -->
<link rel="stylesheet" type="text/css" href="assets/icon/themify-icons/themify-icons.css" />
<!-- Font Awesome -->
<link rel="stylesheet" type="text/css" href="assets/icon/font-awesome/css/font-awesome.min.css" />
<!-- ico font -->
<link rel="stylesheet" type="text/css" href="assets/icon/icofont/css/icofont.css" />
<!-- Style.css -->
<link rel="stylesheet" type="text/css" href="assets/css/style.css" />
<link rel="stylesheet" type="text/css" href="assets/css/jquery.mCustomScrollbar.css" />

<script>
    // Function to set the general object in Local Storage
    function setGeneralToLocalStorage(generalJson) {
        try {
            localStorage.setItem('general', JSON.stringify(generalJson));
        } catch (e) {
            console.error('Error saving to localStorage:', e);
        }
    }

    // Function to update the title from Local Storage
    function updateTitleFromLocalStorage() {
        try {
            var general = JSON.parse(localStorage.getItem('general'));
            if (general && general.nameApp) {
                document.title = general.nameApp;
            }
        } catch (e) {
            console.error('Error updating title from localStorage:', e);
        }
    }

    // Call the functions on window load
    window.onload = function () {
        try {
            var generalLocalStorage = JSON.parse(localStorage.getItem('general'));
            if (generalLocalStorage) {
                setGeneralToLocalStorage(generalLocalStorage);
                updateTitleFromLocalStorage();
            } else {
                var generalJson = '${generalJson}'; // Get the generalJson from the attribute
                if (generalJson.trim().length > 0) {
                    setGeneralToLocalStorage(JSON.parse(generalJson));
                    updateTitleFromLocalStorage();
                } else {
                    console.error('Empty or invalid generalJson:', generalJson);
                }
            }
        } catch (e) {
            console.error('Error parsing or setting generalJson:', e);
        }
    };
</script>

<!-- Head Import End -->
