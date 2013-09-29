modules = {
    app {
        resource url:"css/app.css"
    }

    //applied to partner list view
    list {
        resource url:"css/list.css"
    }

    datepicker {
    	dependsOn "jquery"
    	resource url:"js/bootstrap-datepicker.js"
    	resource url:"js/application.js"
    	resource url:"css/datepicker.css"
    }

    effect {
    	resource url:"js/classie.js"
    	resource url:"js/sidebarEffects.js"
    	resource url:"css/component.css"
    }
}