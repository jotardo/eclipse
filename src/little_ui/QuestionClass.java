package little_ui;

import java.util.Arrays;

public class QuestionClass {

	public class Question {
		private String question;
		private String[] choice;
		private int answer;

		public Question(String question, String[] choice, int answer) {
			this.question = question;
			this.choice = choice;
			this.answer = answer;
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new  StringBuilder();
			sb.append(this.question);
			sb.append('\n');
			sb.append('\n');
			for (int i = 0; i < this.choice.length; i++) {
				sb.append((char) ('A' + i));
				sb.append(". ");
				sb.append(this.choice[i]);
				sb.append('\n');
			}
			return sb.toString().trim();
		}
	}

	private static QuestionClass instance = new QuestionClass();
	private static QuestionClass.Question[] questionArr = {};

	public static QuestionClass.Question getRandomQuestion() {
		return instance.new Question("1+1=?", new String[] {"1", "2", "3", "4"}, 2);
	}

}
