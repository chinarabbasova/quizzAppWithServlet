package entity;


public class Questions {

    private Integer id;

    private String title;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer ans;
    private Integer choose;
    private String email;
 private String password;
    public Questions() {


    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Questions(Integer id, String title, String optionA, String optionB, String optionC, String optionD, Integer ans, Integer choose) {
        this.id = id;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.ans = ans;
        this.choose = choose;
    }
    public Questions( String title, String optionA, String optionB, String optionC, String optionD, Integer ans,String email,String password) {
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.ans = ans;
        this.email = email;
        this.password=password;
    }
    public Questions(Integer id, String title, String optionA, String optionB, String optionC, String optionD, Integer ans, Integer choose, String email) {
        this.id = id;
        this.title = title;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.ans = ans;
        this.choose = choose;
        this.email = email;
    }

    public Questions(Integer id, Integer ans) {
        this.id = id;
        this.ans = ans;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public Integer getAns() {
        return ans;
    }

    public void setAns(Integer ans) {
        this.ans = ans;
    }

    public Integer getChoose() {
        return choose;
    }

    public void setChoose(Integer choose) {
        this.choose = choose;
    }

//    @Override
//    public String toString() {
//        return "Allquestion{" +
//                "ques_id=" + id +
//                ", title='" + title + '\'' +
//                ", optionA='" + optionA + '\'' +
//                ", optionB='" + optionB + '\'' +
//                ", optionC='" + optionC + '\'' +
//                ", optionD='" + optionD + '\'' +
//                ", ans=" + ans +
//                ", choose=" + choose +
//                '}';
//    }
    @Override
    public String toString() {
        return    "ques_id=" + id +  ", ans=" + ans;
    }
}