public class Tool {
    private tool_id;
    private Boolean availability = true;

    // is there a standardized way to create ids?
    public Tool() {
        // set tool_id
    }

    // get tool_id
    public String getToolID() {
        return this.tool_id;
    }

    // true if available
    public Boolean getAvailability() {
        return this.availability;
    }

    // maybe better param name
    public void setAvailability(Boolean is_availability) {
        this.availability = new_availability;
    }
}
