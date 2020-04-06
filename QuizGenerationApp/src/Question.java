/**
 * Abstract class representing a Question.
 * Written by Maria Morales for CMS270
 * November 4, 2019
 */
public abstract class Question {
	private String qtext;
	private String answer;
	private int points;
	
	/**
	 *  Takes no arguments to create a new Question 
	 */
	public Question() {
		
	}
	
	/**
	 * Creates a new Question with the given parameters.
	 * @param question the question text formatted as a String
	 * @param answer the answer formatted as a String
	 */
	public Question(String question, String answer) {
		this.qtext = question;
		this.answer = answer;
		this.points = 0;
	}
	
	/**
	 * Calculates the total score.
	 * @return int denoting total score
	 */
	public abstract int calculateScore();
	
	/**
	 * Gets the question text for this Question.
	 * @return question text as a String
	 */
	public String getQuestionText() {
		return this.qtext.trim();
	}
	
	/**
	 * Changes this Question's text.
	 * @param question new question as a string
	 */
	public void setQuestionText(String question) {
		this.qtext = question;
	}
	
	/**
	 * Gets the answer for this Question.
	 * @return answer as a string
	 */
	public String getAnswer() {
		return this.answer.trim();
	}
	
	/**
	 * Changes this Question's answer. 
	 * @param answer new answer as a string
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * Gets this Question's points.
	 * @return number of points as an integer
	 */
	public int getPoints() {
		return this.points;
	}
	
	/**
	 * Changes this Question's points.
	 * @param points number of points as an integer
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	/**
	 * Describes this Question.
	 * @return string question and string answer
	 */
	public String toString() {
		return this.getQuestionText() + "\nAnswer: " + this.getAnswer();
	}

}
