package dao.inter;

import entity.Questions;

import java.util.List;
import java.util.Map;

public interface QuestionDaoInter {
public List<Questions> getAllQuestions(String title, String optionA, String optionB, String optionC, String optionD, Integer ans, Integer choose);
public List<Questions> getAll();
public Map<Integer, Integer> getByIdAndAns();
public Questions getById(int id);
public boolean updateQuestion(Questions q);

public boolean removeQuestion(int id);
public Questions findByEmail(String email);
public  boolean addQuestion(Questions q);
}
