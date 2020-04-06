import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * Class to generate quizzes from Questions.
 * Written by Maria Morales for CMS270
 * November 4, 2019
 */
public class QuizGenerationApp {
	/**
	 * Reads from a text file and stores all of the Question objects in an ArrayList.
	 * @param file the file in which the questions are written
	 * @return ArrayList<Question> ArrayList containing all the questions found in the file
	 */
	public static ArrayList<Question> makeTestbank(File file) {
		/* Stores the Questions created */
		ArrayList<Question> array = new ArrayList<Question>();
		Scanner scan = null;
		try
	    {
			/* Sets scanner to read from file */
			scan = new Scanner(file);        
			/* Reads each line of the text file and  
		     * creates the appropriate type of Question */
	        while( scan.hasNext()){
	        	String qType = scan.next();
	    
	        	if (qType.equals("B")) {
	        		scan.useDelimiter( "(?<=\\?) |\\n" ); 
	        		/* BasicQuestion is created and added to the ArrayList */
				    BasicQuestion bQuestion = new BasicQuestion(scan.next(), scan.next());
				    array.add(bQuestion);
				    scan.reset();
				} else if (qType.equals("H")) {
					int level = scan.nextInt();
					scan.useDelimiter( "(?<=\\?) |\\n" );
					/* HardQuestion is created and added to the ArrayList */
					HardQuestion hQuestion = new HardQuestion(level, scan.next(), scan.next());
					array.add(hQuestion);
					scan.reset();
				} else if (qType.equals("M")){
					scan.useDelimiter( "(?<=\\?) |\\n" );
					/* MagicQuestion is created and added to the ArrayList */
					MagicQuestion mQuestion = new MagicQuestion(scan.next(), scan.next());
					array.add(mQuestion);
					scan.reset();
				}	
	        	
	        }
	        scan.close();
	    }
	    catch( FileNotFoundException e )
	    {
	    	System.out.println("A FileNotFoundException was caught");
	        e.printStackTrace();
	    }
		
		return array;
	}

	/**
	 * Generates an ArrayList of a given number of random integers within a range.
	 * @param qBank an ArrayList of Question objects whose size is the max number in the range
	 * @param num an integer representing the number of random integers needed
	 * @return ArrayList<Integer> containing the integers generated
	 */
	public static ArrayList<Integer> generateRandomIndices(ArrayList<Question> qBank, int num){
		/* Stores the randomly generated integers */
		ArrayList<Integer> indexNums = new ArrayList<Integer>();
		Random rand = new Random();
		int rIndex = 0;
		
		/* Generates random integers within the range and adds them to the ArrayList */
		while (indexNums.size() < num) {
			rIndex = rand.nextInt((qBank.size() - 1) + 1);
			/* Checks that no duplicates exist in the ArrayList before adding rIndex */
			if(!indexNums.contains(rIndex)) {
				indexNums.add(rIndex);
			}
		}
		return indexNums;
	}

	/**
	 * Generates an array of the given number of Question objects from a testBank.
	 * @param qBank ArrayList<Question> the testBank to obtain the Questions from
	 * @param num int representing the desired number of questions
	 * @return
	 */
	public static Question[] generateQuiz(ArrayList<Question> qBank, int num) {
		/* Stores the questions chosen from qBank*/
		Question[] questions = new Question[num];
		
		/* Calls the generateRandomIndices method to choose the indices of questions in the qBank*/
		ArrayList<Integer> indexNums = generateRandomIndices(qBank, num);
		
		/* Fills out the array with questions from qBank*/
		for (int i = 0; i < questions.length; i ++) {
			questions[i] = qBank.get(indexNums.get(i));
		}
		return questions;
	}

	/**
	 * Writes the questions from a given array to a text file.
	 * @param quiz an array of Question objects
	 */
	public static void saveQuiz(Question[] quiz) {
		PrintWriter p = null;
		try {
			p = new PrintWriter("quiz.txt");
			
			/* Gets the Questions and writes them to a text file */
			for (int i = 0; i < quiz.length; i++) {
				p.println((i+1) + ". " + quiz[i].getQuestionText() + "\n");
			}
			p.close();
		} catch (FileNotFoundException e) {
			System.out.println("A FileNotFoundException was caught");
			e.printStackTrace();
		}
	}

	/**
	 * Writes the questions and answers from a given array to a text file creating a key.
	 * @param quiz an array of Question objects
	 */
	public static void generateKey(Question[] quiz) {
		PrintWriter p = null;
		try {
			p = new PrintWriter("key.txt");
			
			/* Gets the Questions and their answers and writes them to a text file */
			for (int i = 0; i < quiz.length; i++) {
				p.println((i+1) + ". " + quiz[i].getQuestionText());
				p.println("   Answer: " + quiz[i].getAnswer() + "\n");
			}
			p.close();
		} catch (FileNotFoundException e) {
			System.out.println("A FileNotFoundException was caught");
			e.printStackTrace();
		}
	}

	/**
	 * Allows the user to take the given quiz interactively.
	 * @param quiz an array of Question objects
	 */
	public static void takeQuiz(Question[] quiz) {
		/* Keeps the user score*/
		int score = 0;
		/* Counts the number of questions answered correctly*/
		int count = 0;
		/* Scanner to read answers from the user */
		Scanner scan = new Scanner(System.in);
		/* Stores user answer */
		String userAnswer;
		for (int i = 0; i < quiz.length; i++) {
			/* Print question and read answer */
			System.out.print(quiz[i].getQuestionText() + " ");
			userAnswer = scan.nextLine();
			
			/* Check if answer is correct */
			if (userAnswer.equalsIgnoreCase(quiz[i].getAnswer())) {
				score += quiz[i].calculateScore();
				count++;
				
				/* Check if question is the last one and print score accordingly */
				if (i == (quiz.length - 1)) {
					System.out.println("Correct.\nYour total score is " + score);
					System.out.println( "You answered " + count + " out of "
					+ quiz.length + " questions correctly");
				} else {
					System.out.println("Correct. Your score is " + score);
					System.out.println();
				}
				
			} else {
				/* Check if question is the last one and print score accordingly */
				if (i == (quiz.length - 1)) {
					System.out.println("Incorrect. The correct answer was " + 
					quiz[i].getAnswer() + ".\nYour total score is " + score);
					System.out.println( "You answered " + count + " out of "
							+ quiz.length + " questions correctly");
				} else {
					System.out.println("Incorrect. The correct answer was " + 
					quiz[i].getAnswer() + ".\nYour score is " + score);
					System.out.println();
				}
			}
			
		}
		scan.close();
	}

	/**
	 * Determines if the user wants to save the quiz, generate a key, or take it.
	 * @param userChoice int denoting the user choice
	 * @param quiz an array of Question objects
	 */
	public static void determineAction (int userChoice, Question[] quiz) {
		/* Determines behavior associated with user input and prints/writes accordingly */
		if (userChoice == 1) {
			saveQuiz(quiz);
			System.out.println("Your quiz was created goodbye.");
		} else if (userChoice == 2) {
			generateKey(quiz);
		} else {
			System.out.println("Ok let us begin the quiz\n");
			takeQuiz(quiz);
		} 
	}
	
	
	public static void main(String[] args) {
		/* Reads user's input */
		Scanner scan = new Scanner(System.in);
		
		/* Reads Questions from a text file and stores them */
		ArrayList<Question> questionBank = makeTestbank(new File ("questions.txt"));
		
		/* Beginning of the game */
		System.out.println("Welcome to the Quiz Generator App"); 
		System.out.print("How many questions would you like in the quiz? ");
		
		/* Stores user input */
		int numOfQuestions = scan.nextInt();
		
		/* Verify validity of input against the size of the questionBank before moving on */
		while (numOfQuestions > questionBank.size() || numOfQuestions <= 0) {
			System.out.println("The given number is not valid. Please try a different number.");
			numOfQuestions = scan.nextInt();
		} 
		
		/* Stores the quiz generated */
		Question[] quiz = generateQuiz(questionBank, numOfQuestions);
		
		System.out.print("Select 1 to save quiz, 2 to generate a key or 3 to take the quiz ");
		/* Stores user input */
		int userChoice = scan.nextInt();
		
		/* Verify validity of user's input against possible choices before moving on */
		while (userChoice < 1 || userChoice > 3) {
			System.out.println("Invalid input. Please try again.");
			userChoice = scan.nextInt();
		}
		
		/* Determine what to do with the quiz based on user's choice and exit the system when done */
		determineAction(userChoice, quiz);
		
		scan.close();
	}
}
