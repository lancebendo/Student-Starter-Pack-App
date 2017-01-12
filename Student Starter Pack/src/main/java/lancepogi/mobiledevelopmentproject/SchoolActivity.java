package lancepogi.mobiledevelopmentproject;

/**
 * Created by Lance on 1/12/2017.
 */

public class SchoolActivity {

    private int id;
    private String act_name;
    private String desc;
    private boolean isDone;
    private String deadline;

    public SchoolActivity(String act_name, String desc, String deadline) {
        this.act_name = act_name;
        this.desc = desc;
        this.deadline = deadline;
        this.isDone = false;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public int getID() {
        return this.id;
    }

    public String getAct_name() {
        return this.act_name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

}
