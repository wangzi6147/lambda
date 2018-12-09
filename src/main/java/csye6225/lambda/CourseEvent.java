package csye6225.lambda;

public class CourseEvent {
    private String courseId;
    private String department;
    private String boardId;

    public CourseEvent() {

    }
    public CourseEvent(String courseId, String department) {
        this.courseId = courseId;
        this.department = department;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        return "{\"courseId\": \"" + courseId + "\", \"department\": \"" + department +
                "\", \"boardId\": \"" + boardId + "\"}";
    }
}
