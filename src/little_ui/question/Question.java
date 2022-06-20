package little_ui.question;

import java.util.Arrays;

public class Question {

	private static final String imagePattern = "img:\".+\"";
	private String question;
	private String[] choices;
	private int correctAnswer;
	
	public Question(String question, String[] choices, int correctAnswer) {
		this.question = question;
		this.choices = choices;
		this.correctAnswer = correctAnswer;
	}
	
	public static Question[] makeRandomQuestion(int amount) {
		if (amount <= 0) {
			System.out.println("No you can't");
			return null;
		}
		Question[] qArr = new Question[amount];
		for (int i = 0; i < qArr.length; i++) {
			qArr[i] = new Question("1 + 1 = ...?|img:\"res/ex_3/plus.png\"|\nPlease choose", new String[] {"1", "2", "3", "4"}, 2);
		}
		return qArr;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String[] getChoices() {
		return choices;
	}
	
	public int getCorrectAnswer() {
		return correctAnswer;
	}
	
	public static String getPattern(){
		return imagePattern;
	}
}
