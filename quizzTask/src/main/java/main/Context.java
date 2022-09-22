package main;

import dao.impl.QuestionDaoImpl;
import dao.inter.QuestionDaoInter;

public class Context {
    public static QuestionDaoInter instanceQuesDao(){
        return new QuestionDaoImpl();
    }

}
