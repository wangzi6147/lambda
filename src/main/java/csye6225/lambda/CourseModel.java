package csye6225.lambda;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.List;

@DynamoDBTable(tableName = "Course")
public class CourseModel {

    private String id;
    private String courseId;
    private String professorId;
    private String TAId;
    private String department;
    private String boardId;
    private List<String> roster;
    private String topic;

    public CourseModel() {

    }

    @DynamoDBHashKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName = "courseId")
    @DynamoDBIndexHashKey(attributeName = "courseId", globalSecondaryIndexName = "courseId-index")
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @DynamoDBAttribute(attributeName = "professorId")
    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    @DynamoDBAttribute(attributeName = "taId")
    public String getTAId() {
        return TAId;
    }

    public void setTAId(String TAId) {
        this.TAId = TAId;
    }

    @DynamoDBAttribute(attributeName = "department")
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @DynamoDBAttribute(attributeName = "boardId")
    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    @DynamoDBAttribute(attributeName = "roster")
    public List<String> getRoster() {
        return roster;
    }

    public void setRoster(List<String> roster) {
        this.roster = roster;
    }

    @DynamoDBAttribute(attributeName = "topic")
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
