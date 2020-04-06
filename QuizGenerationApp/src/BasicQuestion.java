/**
 * Inherits from Question and represents a basic question.
 * Written by Maria Morales for CMS270
 * November 4, 2019
 */
public class BasicQuestion extends Question{

	/**
	 * Takes no arguments to create a new BasicQuestion.
	 */
	public BasicQuestion() {
		
	}
	
	/**
	 * Creates a new BasicQuestion with the given parameters.
	 * @param question the question text formatted as a String
	 * @param answer the answer formatted as a String
	 */
	public BasicQuestion(String question, String answer) {
		super(question, answer);
		this.setPoints(5);
	}

	/**
	 * Calculates the total score for BasicQuestion.
	 * @return int representing total score
	 */
	public int calculateScore() {
		return this.getPoints();
	}
	
	/**
	 * Describes this BasicQuestion.
	 * @return string question and string answer			  
	 */
	public String toString() {
		return this.getQuestionText() + "\nAnswer: " + this.getAnswer();
	}

}
