package resources;
//enum is a special class in java which has a collection of constants and methods.
public enum APIResources {

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");

    private String resource;

    APIResources(String resourse) {
        this.resource = resourse;
    }
    public String getResource() {
        return resource;
    }

}

