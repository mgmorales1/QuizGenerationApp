/**
 * Inherits from BasicQuestion and represents a hard question.
 * Written by Maria Morales for CMS270
 * November 4, 2019
 */
public class HardQuestion extends BasicQuestion{
	private int level;
	
	/**
	 *  Takes no arguments to create a new HardQuestion
	 */
	public HardQuestion() {
		
	}
	/**
	 * Creates a new HardQuestion with the given parameters.
	 * @param level integer the level of difficulty formatted as an int
	 * @param question the question formatted as a String
	 * @param answer the answer formatted as a String
	 */
	public HardQuestion(int level, String question, String answer ) {
		super(question, answer);
		this.level = level;
		if (this.level > 2) {
			this.setPoints(20);
		} else {
			this.setPoints(10);
		}
	}
	
	/**
	 * Gets the level of difficulty for this HardQuestion.
	 * @return int denoting level of difficulty 
	 */
	public int getLevel() {
		return this.getLevel();
	}
	
	/**
	 * Changes this HardQuestion's level of difficulty
	 * @param level the level of difficulty formatted as an int
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Calculates the total score based on level of difficulty.
	 * @return int denoting total score
	 */
	public int calculateScore() {
		return this.getPoints();
	}
	
	/**
	 * Describes this HardQuestion.
	 * @return string question, string answer, and int level of difficulty
	 */
	public String toString() {
		return this.getQuestionText() + "\nAnswer: " + this.getAnswer()
		+ "\nLevel of difficulty: " + this.getLevel();
	}
	
}
