package entity;

public class Student {    //学生类
    private String name;
    private String id;
    private String course;
    private String score;

    public Student(String id, String name, String course, String score){
        this.name = name;
        this.id = id;
        this.course = course;
        this.score = score;
    }

    public Student(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return id + ',' +
                name + ',' +
                course + ',' +
                score;
    }
}
