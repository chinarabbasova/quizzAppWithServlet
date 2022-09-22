package dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.inter.AbstractDao;
import dao.inter.QuestionDaoInter;
import entity.Questions;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class QuestionDaoImpl extends AbstractDao implements QuestionDaoInter {

    private Questions getQuestion(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String optionA = rs.getString("optionA");
        String optionB = rs.getString("optionB");
        String optionC = rs.getString("optionC");
        String optionD = rs.getString("optionD");
        int ans = rs.getInt("ans");
        int choose = rs.getInt("choose");
        String email = rs.getString("email");
        return (new Questions(id, title, optionA, optionB, optionC, optionD, ans, choose,email));
    }

    @Override
    public List<Questions> getAllQuestions(String title, String optionA, String optionB, String optionC, String optionD, Integer ans, Integer choose) {
        List<Questions> result = new ArrayList<>();
        try (Connection c = connect()) {
            String sql = "select * from questions q";

            if (title != null && !(title.trim().isEmpty())) {
                sql += "and q.title ";
            }
            if (optionA != null && !(optionA.trim().isEmpty())) {
                sql += "and q.optionA";
            }
            if (optionB != null && !(optionB.trim().isEmpty())) {
                sql += "and q.optionB";
            }
            if (optionC != null && !(optionC.trim().isEmpty())) {
                sql += "and q.optionC";
            }
            if (optionD != null && !(optionD.trim().isEmpty())) {
                sql += "and q.optionD";
            }
            if (ans != null) {
                sql += "and q.ans";
            }
            if (choose != null) {
                sql += "and q.choose";}
//            if(1==1){
//                sql+="order by id";
//            }
            PreparedStatement stmt = c.prepareStatement(sql);
            int i = 1;
            if (title != null && !(title.trim().isEmpty())) {
                stmt.setString(i, title);
                i++;
            }
            if (optionA != null && !(optionA.trim().isEmpty())) {
                stmt.setString(i, optionA);
                i++;
            }
            if (optionB != null && !(optionB.trim().isEmpty())) {
                stmt.setString(i, optionB);
                i++;
            }
            if (optionC != null && !(optionC.trim().isEmpty())) {
                stmt.setString(i, optionC);
                i++;
            }
            if (optionD != null && !(optionD.trim().isEmpty())) {
                stmt.setString(i, optionD);
                i++;
            }
            if (ans != null) {
                stmt.setInt(i, ans);
                i++;
            }
            if (choose != null) {
                stmt.setInt(i, choose);
                i++;
            }
            stmt.execute();
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                Questions q = getQuestion(rs);
                result.add(q);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override

    public List<Questions> getAll() {
        List<Questions> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement st = c.createStatement();
            st.execute("select * from questions q order by id");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                int ans = rs.getInt("ans");
                int choose = rs.getInt("choose");
                result.add(new Questions(id, title, optionA, optionB, optionC, optionD, ans, choose));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getByIdAndAns() {
        Map<Integer, Integer> map = new HashMap<>();
        try (Connection c = connect()) {
            Statement st = c.createStatement();
            st.execute("select * from questions q ");
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                int id = rs.getInt("id");
                int ans = rs.getInt("ans");
                //  result.add(new Questions(id,title,optionA,optionB,optionC,optionD,ans,choose));
                map.put(id, ans);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return map;
    }

    @Override
    public Questions getById(int id) {
        Questions result = null;
        try (Connection c = connect();
             Statement stmt = c.createStatement()) {
            stmt.execute("select * from questions  q where q.id=" + id);
            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                result = getQuestion(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateQuestion(Questions q) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update questions set title=?,\"optionA\"=?,\"optionB\"=?,\"optionC\"=?,\"optionD\"=?,ans=? where id=?");
            stmt.setString(1, q.getTitle());
            stmt.setString(2, q.getOptionA());
            stmt.setString(3, q.getOptionB());
            stmt.setString(4, q.getOptionC());
            stmt.setString(5, q.getOptionD());
            stmt.setInt(6, q.getAns());
            stmt.setInt(7, q.getId());

            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {

//        QuestionDaoInter questionDaoInter = new QuestionDaoImpl();
//        List<Questions> list= questionDaoInter.getAll();
//        System.out.println(Arrays.toString(list.toArray()));
//        for (int i=1; i<=list.size();i++){
//
//            List<Integer> ID = list.stream().map(x->x.getId()).collect(Collectors.toList());
////            List<Integer> ID2 = list.stream().map(x->x.getAns()).collect(Collectors.toList());
//            System.out.println(Arrays.toString(ID.toArray()));
//        }


//        QuestionDaoInter questionDaoInter = new QuestionDaoImpl();
//        Questions q= new Questions();
//        q.setTitle("hello world");
//        q.setOptionA("ha?");
//        q.setOptionB("hi");
//        q.setOptionC("hello");
//        q.setOptionD("hello");
//        q.setAns(1);
//        q.setId(1);
//        questionDaoInter.updateQuestion(q);

//        QuestionDaoInter ques = new QuestionDaoImpl();
//       Questions q = new Questions("haha","ksks","ajscn","skdjcn","skdjcn",1,"@mail","12345");
//        System.out.println( ques.addQuestion(q));

//        QuestionDaoImpl questionDao = new QuestionDaoImpl();
//        //System.out.println(questionDao.getByIdAndAns().toString());
//        Map<Integer, Integer> mm = questionDao.getByIdAndAns();
//        System.out.println(Arrays.asList(mm));
    }
//    @Override
//    public Questions findByName(String name) {
//        Questions result = null;
//        try (Connection c = connect()) {
//            PreparedStatement stmt = c.prepareStatement("select  * from questions q where name=?");
//            stmt.setString(1, name);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                result = getQuestionSimple(rs);
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

    @Override
    public boolean removeQuestion(int id) {
        try (Connection c = connect();
             Statement stmt = c.createStatement();
        ) {
            return stmt.execute("delete from questions where  id= " + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Questions findByEmail(String email) {
        Questions result = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select * from questions where email=? ");
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result = getQuestionSimple(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;

    }
    private static BCrypt.Hasher crypt = BCrypt.withDefaults();

    @Override
    public boolean addQuestion(Questions q) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into questions (title, \"optionA\", \"optionB\", \"optionC\", \"optionD\", ans,  email, password) values (?,?,?,?,?,?,?,?)");
            stmt.setString(1, q.getTitle());
            stmt.setString(2, q.getOptionA());
            stmt.setString(3, q.getOptionB());
            stmt.setString(4, q.getOptionC());
            stmt.setString(5, q.getOptionD());
            stmt.setInt(6, q.getAns());
            stmt.setString(7, q.getEmail());
            stmt.setString(8, crypt.hashToString(4, q.getPassword().toCharArray()));
            return stmt.execute();
        }catch (Exception ex) {
            ex.getMessage();
            return false;
        }
    }
    private Questions getQuestionSimple(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String optionA = rs.getString("optionA");
        String optionB = rs.getString("optionB");
        String optionC = rs.getString("optionC");
        String optionD = rs.getString("optionD");
        int ans = rs.getInt("ans");
        int choose = rs.getInt("choose");
        String email = rs.getString("email");


        Questions q = new Questions(id,title,optionA,optionB,optionC,optionD,ans,choose,email);
        q.setPassword(rs.getString("password"));
        return q;
    }
//    public static void main(String[] args) {
//        QuestionDaoImpl questionDao = new QuestionDaoImpl();
//        System.out.println(questionDao.getAll());
//    }
}
