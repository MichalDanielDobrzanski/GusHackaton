package com.gus.hackaton.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "question")
public class Question
{
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "question")
    private String question;

    @ColumnInfo(name = "answer1")
    private String answer1;

    @ColumnInfo(name = "answer2")

    private String answer2;

    @ColumnInfo(name = "answer3")
    private String answer3;

    @ColumnInfo(name = "answer4")
    private String answer4;

    @ColumnInfo(name = "correct_answer")
    private int correctAnswer;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String[] getAnswers()
    {
        return new String[] {answer1, answer2, answer3, answer4};
    }

    public void setAnswers(String[] answers)
    {
        this.answer1 = answers[0];
        this.answer2 = answers[1];
        this.answer3 = answers[2];
        this.answer4 = answers[3];
    }

    public int getCorrectAnswer()
    {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer)
    {
        this.correctAnswer = correctAnswer;
    }

    public String getAnswer1()
    {
        return answer1;
    }

    public void setAnswer1(String answer1)
    {
        this.answer1 = answer1;
    }

    public String getAnswer2()
    {
        return answer2;
    }

    public void setAnswer2(String answer2)
    {
        this.answer2 = answer2;
    }

    public String getAnswer3()
    {
        return answer3;
    }

    public void setAnswer3(String answer3)
    {
        this.answer3 = answer3;
    }

    public String getAnswer4()
    {
        return answer4;
    }

    public void setAnswer4(String answer4)
    {
        this.answer4 = answer4;
    }
}
