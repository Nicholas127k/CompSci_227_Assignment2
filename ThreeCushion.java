package hw2;

import api.BallType;
import static api.BallType.RED;
import static api.BallType.WHITE;
import static api.BallType.YELLOW;
import api.PlayerPosition;
import static api.PlayerPosition.PLAYER_A;
import static api.PlayerPosition.PLAYER_B;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @NicholasG.Kirschbaum!!!
 */
public class ThreeCushion {
	
	
	
	
	// TODO: EVERTHING ELSE GOES HERE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.

	 

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.
	
	
	//this variable helps sort out cueStickStrike and cueBallStrike to make sure there is no 
	// errors
	private int whatBall = 0;
	
	// this integer keeps the score of player 1 
	private int aScore = 0;
	// this integer keeps the score of player 2
	private int bScore = 0;
	// this integer keeps track of innings
	private int inning = 0;
	
	private int breakshotCounter = 0;
	// lagWinner takes the initial player to help decided who is currently playing
	private PlayerPosition lagWinner;
	
	
	// this counts how many cue ball impacts there is
	private int impact = 0;
	// counts if a point was just scored
	private int newpoint = 0;
	// If this variable gets set to one game is over
	private int isgameover = 0;
	//This counts to see if you hit the other two balls 
	private int struck = 0;
	// checks to see if a shot is a bank shot
	private int bank = 0;
	// if foul committed it is equal to 1;
	private int foulCommited = 0;
	// this takes the initial threecushion pointTowin value and helps check to see if someone 
	//has won
	private int pointsToWin;
	// this helps with rule 3a
	private int breakshot = 1;
	// i was trying to use this for problem 24 couldn't figure it out
	private int stopstrick = 0;
	
	
	
	// This make sure that code like foul doesn't run before the lagWinnerchooses is run
	private int hasStarted = 0;
	// This make sure that the game has started before choosing who's turn it is
	private int allowed = 0;
	
	// this variable keeps track of what color the ball is
	private BallType Color;
	// this make sure that red doesn't get caught recounting in strike
	private int redColor = 0;
	// this make sure that white doesn't get caught causing errors
	private int whiteColor = 0;
	
	
	
	
	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 
	 */
	// constructor
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		// sets up initial values with what is giving 
		inning = 1;
		
		this.lagWinner = lagWinner;
		this.pointsToWin = pointsToWin;
		
		
		
	}
	// methods
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
	
		//this allows other code to run (prevents premature running)
		allowed = 1;
		//this initially sets color
		this.Color = cueBall;
		
		
		if (selfBreak == false) {
			
			if(lagWinner == PLAYER_B) {
				//this code could cause problems tbd
				lagWinner = PLAYER_A;
				
			
				
			}
			else {
				lagWinner = PLAYER_B;
				Color = YELLOW;
				
			}
			
			
			
		}
		
		
	}
	
	
	public void cueStickStrike(BallType ball) {
		// this make sure that the code isn't running before it should be
		if ((ball == YELLOW) || (ball == WHITE)) {
			stopstrick = 1;
		}
		
		if (ball != Color) {
			foul();
			
			
		}
		
			
		
			
				
		
			// this checks to see if a foul has been committed before running this
			if (foulCommited != 1) {
				if (isgameover != 1) {
					hasStarted = 1;
				}
				else {
					hasStarted = 0;
				}
				
				bank = 0;
				
				if (ball == WHITE) {
					whatBall = 1;
					
					
					
				}
				else if (ball == YELLOW) {
					whatBall = 2;
					
					
				}
				else if (ball == RED) {
					whatBall = 3;
					breakshot = 0;
				}
			
			}
		
		
	}
	public void cueBallStrike(BallType ball) {
		
		// this make sure that the code isn't running before it should be
				
		
		
		if ((ball == WHITE) && (whatBall != 1)) {
			// set it to a number from 1 to 3 and then use it else where
			struck = struck + 1;
			if (breakshot == 1) {
				inning = inning - 1;
				foul();
				breakshot = 0;
				whiteColor = whiteColor + 1;
				
				
			}
			
		}
		else if ((ball == YELLOW) && (whatBall != 2)) {
			struck = struck + 1;
			if (breakshot == 1) {
				inning = inning - 1;
				foul();
				breakshot = 0;
				
				
			}
			
		}
		else if ((ball == RED) && (whatBall != 3)) {
			struck = struck + 1;	
			breakshot = 0;
			if (redColor == 2) {
				bank = 0;
			}
			redColor = redColor + 1;
			
		}
		
		
	}
	public void cueBallImpactCushion() {
		
		
		
			impact = impact + 1;
			if (breakshot == 1) {
				
				foul();
				breakshot = 0;
				
				
			}
		
		//checks to see if it was a bank shot
		if ((impact == 3) && (struck == 0)) {
			bank = 1;
		}
	}
	
	
	
	
	
	
	
	
	
	public void endShot() {
		// this make sure that the code isn't running before it should be
		if ((allowed == 0) || (isgameover == 1)) {
			return;
		}
		if (stopstrick == 0) {
			return;
			
		}
		// this adds the points
		
			if ((struck >= 2) && (impact >= 3)) {
				
				if (lagWinner == PLAYER_A) {
					aScore = aScore + 1;
					newpoint = 1;
				}
				else {
					bScore = bScore + 1;
					newpoint = 1;
				}
				
			}
		
		if (newpoint != 1) {
			// this resets if the code has started or not
			hasStarted = 0;
		}           
		// break might have to be placed else where
		breakshotCounter = 1;
		if (newpoint != 1) {
			// this changes player
			if (foulCommited != 1) {
				
				if(lagWinner == PLAYER_A) {
					
					lagWinner = PLAYER_B;
				}
				else {
					
					lagWinner = PLAYER_A;
				}
				if(Color == WHITE) {
					Color = YELLOW;
					
				}
				else {
					Color = WHITE;
					
				}
				inning = inning + 1;
			}
		}
		// this prints out final results
		if ((aScore == pointsToWin) || (bScore == pointsToWin)) {
			isgameover = 1;
			
		}
		// This resets values back to 0 so they can rerun
		struck = 0;
		impact = 0;
		whatBall = 0;
		foulCommited = 0;
		newpoint = 0;
		breakshot = 0;
		stopstrick = 0;
		
	}
	public void foul() {
		// this resets if the code has started or not
		
		if ((allowed == 0) || (isgameover == 1)) {
			return;
		}
		//adds an inning
		if (isgameover == 0) {
			inning = inning + 1;
		}
		
		hasStarted = 0;
		
		// this changes player
		if(lagWinner == PLAYER_A) {
			
			lagWinner = PLAYER_B;
		}
		else {
			
			lagWinner = PLAYER_A;
		}
		if(Color == WHITE) {
			Color = YELLOW;
			
		}
		else {
			Color = WHITE;
			
		}
		foulCommited = 1;
		
	}
	public int getPlayerAScore() {
		return aScore;
	}
	public int getPlayerBScore() {
		return bScore;
	}
	public int getInning() {
		return inning;
	}
	public BallType getCueBall() {
		return Color;
	}
	public PlayerPosition getInningPlayer() {
		
			
		
		return lagWinner;
		
		
	}
	
	
	public boolean isBreakShot() {
		if (breakshotCounter == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isBankShot() {
		if (bank == 1) {
			return true;
		}
		return false;
		
	}
	public boolean isShotStarted() {
		if (isgameover == 1) {
			return false;
		}
		if (hasStarted == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isInningStarted() {
		
		
		if (hasStarted == 0) {
			return false;
		}
		else {
			return true;
		}
		
	}
	public boolean isGameOver() {
		// if the game is over it inables string to print "game result final"
		if (isgameover == 1) {
			
			return true;
			
		}
		else {
			return false;
		}
		
		
	}
	
	
	
	
	
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (allowed == 1) {
			if (getInningPlayer() == PLAYER_A) {
				playerATurn = "*";
			} else if (getInningPlayer() == PLAYER_B){
				playerBTurn = "*";
			}
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}
