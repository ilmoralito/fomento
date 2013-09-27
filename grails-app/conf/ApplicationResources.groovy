modules = {
    app {
        resource url:"css/app.css"
    }

    datepicker {
    	dependsOn "jquery"
    	resource url:"js/bootstrap-datepicker.js"
    	resource url:"js/application.js"
    	resource url:"js/tooltips.js"
    	resource url:"css/datepicker.css"
    }
}