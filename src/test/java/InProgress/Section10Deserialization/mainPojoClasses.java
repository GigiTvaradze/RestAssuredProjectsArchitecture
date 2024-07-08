package InProgress.Section10Deserialization;

//POJO classes - Plain Object Java Classes

public class mainPojoClasses {

    private String url;
    private String services;
    private String expertise;
    private childCoursesPojoClasses courses; //return object of childCoursesPojoClasses class
    private String instructor;
    private String linkedIn;

    public childCoursesPojoClasses getCourses() {
        return courses;
    }

    public void setCourses(childCoursesPojoClasses courses) {
        this.courses = courses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

};







//{
//    "message" = "Hello",
//     "greet" = "Hi"
//}

//message is key and Hello is value, for all keys in Json need to create variable
//for this variable generate Getter and Setter
