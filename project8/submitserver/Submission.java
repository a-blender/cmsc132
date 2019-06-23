//SUBMISSION CLASS by Anna Blendermann
package submitserver;

//SUBMISSION CLASS********************************************************
//submission class models one student submission to the server
//contains one project number and corresponding score
public class Submission 
{
	protected int projectNumber;
	protected int score;

	public Submission(int projectNumber, int score)
	{
		this.projectNumber = projectNumber;
		this.score = score;
	}

	//GET PROJECT NUMBER METHOD*******************************************
	//gets the project number for a particular submission
	public int getProjectNumber()
	{
		return this.projectNumber;
	}

	//GET PROJECT NUMBER METHOD*******************************************
	//gets the project number for a particular submission
	public int getProjectScore()
	{
		return this.score;
	}

	//SET PROJECT NUMBER METHOD*******************************************
	//resets the project number for a particular submission
	public void setProjectNumber(int num)
	{
		this.projectNumber = num;
	}

	//SET PROJECT SCORE METHOD********************************************
	//resets the score for a particular submission (more popular)
	public void setProjectScore(int score)
	{
		this.score = score;
	}
}