import java.util.Random;
/**
 * Inherits from BasicQuestion and represents a magic question.
 * Written by Maria Morales for CMS270
 * November 4, 2019
 */
public class MagicQuestion extends BasicQuestion {
	private int magicfactor;
	
	/**
	 *  Takes no arguments to create a new HardQuestion.
	 */
	public MagicQuestion() {
		
	}
	
	// magicQ's score is just 5 until you calculate the score?
	/**
	 * Creates a new MagicQuestion with the given parameters.
	 * @param question the question formatted as a String
	 * @param answer the answer formatted as a String
	 */
	public MagicQuestion(String question, String answer) {
		super(question, answer);
		Random rand = new Random();
		this.magicfactor = rand.nextInt((7 - 1) + 1) + 1;
	}
	
	/**
	 * Gets the magic factor for this MagicQuestion.
	 * @return int representing the magic factor
	 */
	public int getMagicFactor() {		
		return this.magicfactor;
	}
	
	// Should I assume the factor will always be right?
	/**
	 * Changes the magic factor of this MagicQuestion.
	 * @param factor integer representing the new magic factor
	 */
	public void setMagicFactor(int factor) {
		this.magicfactor = factor;
	}
	
	//Should get score and calculate score return the same?
	/**
	 * Calculates the score using the magic factor.
	 * @return int denoting the total score
	 */
	public int calculateScore() {
		return this.getPoints() * this.getMagicFactor();
	}
	
	/**
	 * Describes this MagicQuestion.
	 * @return string question, string answer, and int magic factor
	 */
	public String toString() {
		return this.getQuestionText() + "\nAnswer: " + this.getAnswer()
		+ "\nMagic factor: " + this.getMagicFactor();
	}
	

}
